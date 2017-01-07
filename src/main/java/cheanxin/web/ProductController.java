package cheanxin.web;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.global.Constants;
import cheanxin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by 273cn on 17/01/07.
 */
@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> getProducts(
            @RequestParam(value = "productTemplateId", defaultValue = "-1") long productTemplateId,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "status", defaultValue = "0") int status,
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_SIZE) int size) {
        return productService.getProducts(productTemplateId, name, status, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> get(@PathVariable(value = "id") long id) {
        Product product = productService.findOne(id);
        if (product == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> add(
            @Valid @RequestBody Product product,
            @AuthenticationPrincipal User user) {
        Assert.notNull(user, "Unauthorized User.");
        Assert.isTrue(product.getStatus().intValue() == ProductStatus.DRAFT.value(), "Product is not in draft status.");

        product.setCreatorUsername(user.getUsername());
        long now = System.currentTimeMillis() / 1000;
        product.setCreatedTime(now);
        product.setModifiedTime(now);
        return new ResponseEntity<>(save(product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Product product,
            @AuthenticationPrincipal User user) {
        Product unsavedProduct = productService.findOne(id);
        if (unsavedProduct == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));

        Assert.isTrue(unsavedProduct.getStatus().intValue() == ProductStatus.DRAFT.value(), "This product can not be modified, not in draft status..");
        Assert.isTrue(unsavedProduct.getCreatorUsername().equals(user.getUsername()), "Current user is not owner of this product.");

        product.setId(id);
        product.setModifiedTime(System.currentTimeMillis() / 1000);
        return new ResponseEntity<>(save(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") long id) {
        productService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Product> updateStatus(
            @PathVariable(value = "id") long id,
            @RequestBody Product product,
            @AuthenticationPrincipal User user) {
        Product unsavedProduct = productService.findOne(id);
        if (unsavedProduct == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));
        int toStatus = product.getStatus().intValue();
        if (ProductStatusTransfer.hasAuthority(user, unsavedProduct, toStatus))
            throw new UnauthorizedException(user.getUsername());
        return new ResponseEntity<>(productService.review(unsavedProduct, toStatus), HttpStatus.OK);
    }

    private Product save(Product product) {
        Assert.isTrue(LoanPolicy.contains(product.getLoanPolicy().intValue()), "Product's loan policy does not exist.");
        Assert.isTrue(ProductType.contains(product.getProductType().intValue()), "Product's product type does not exist.");
        Assert.isTrue(RepaymentMethod.contains(product.getRepaymentMethod().intValue()), "Product's repayment method does not exist.");
        Assert.isTrue(product.getMinAvailableRate() <= product.getMaxAvailableRate(), "Product's min available rate should be less than max available rate.");

        if (product.getProductTemplateId() == 0L) {
            product.setProvinceId(0L);
            product.setCityId(0L);
        } else {
            long productTemplateId = product.getProductTemplateId().longValue();
            Product productTempalte = productService.findOne(productTemplateId);
            if (productTempalte == null) throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(productTemplateId));
            Assert.isTrue(product.getMinAvailableRate() >= productTempalte.getMinAvailableRate(), "Product's min available rate should be greater than product template's min available rate.");
            Assert.isTrue(product.getMaxAvailableRate() <= productTempalte.getMaxAvailableRate(), "Product's max available rate should be less than product template's max available rate.");
            Assert.isTrue(productTempalte.getAvailableTerms().containsAll(product.getAvailableTerms()), "Product's terms are unavailable.");
            Assert.isTrue(product.getProvinceId() > 0L, "Product's city id should be assigned.");
            Assert.isTrue(product.getCityId() > 0L, "Product's city id should be assigned.");
        }

        return productService.save(product);
    }
}
