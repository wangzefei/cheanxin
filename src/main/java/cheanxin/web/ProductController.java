package cheanxin.web;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.constant.ConfigConstants;
import cheanxin.service.ProductService;
import cheanxin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
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
    public Page<Product> list(
            @RequestParam(value = "productTemplateId", defaultValue = "-1") long productTemplateId,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "status", defaultValue = "-1") int status,
            @RequestParam(value = "page", defaultValue = ConfigConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = ConfigConstants.DEFAULT_SIZE) int size) {
        return productService.list(productTemplateId, name, status, page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Product> get(@PathVariable(value = "id") long id) {
        Product product = productService.getOne(id);
        Assert.notNull(product, "Product not found.");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> save(
            @Valid @RequestBody Product unsavedProduct,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);
        Assert.isTrue(unsavedProduct.getStatus().intValue() == ProductStatus.PENDING_REVIEW.value(), "Product is not in pending review status.");

        unsavedProduct.setCreatorUsername(user.getUsername());
        long now = System.currentTimeMillis() / 1000;
        unsavedProduct.setCreatedTime(now);
        unsavedProduct.setModifiedTime(now);
        return new ResponseEntity<>(save(unsavedProduct), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> update(
            @PathVariable(value = "id") long id,
            @Valid @RequestBody Product unsavedProduct,
            Errors errors,
            @AuthenticationPrincipal User user) {
        String errorMessage = errors.hasErrors() ? errors.getAllErrors().get(0).getDefaultMessage() : null;
        Assert.isNull(errorMessage, errorMessage);

        Product savedProduct = productService.getOne(id);
        Assert.notNull(savedProduct, "Product not found.");
        Assert.isTrue(savedProduct.getStatus().intValue() == ProductStatus.PENDING_REVIEW.value(), "Only product of status pending review can be modified.");
        Assert.isTrue(savedProduct.getCreatorUsername().equals(user.getUsername()), "Current user is not owner of this product.");

        // attributes below can not be modified.
        unsavedProduct.setId(id);
        unsavedProduct.setProductTemplateId(savedProduct.getProductTemplateId());
        unsavedProduct.setStatus(savedProduct.getStatus());
        unsavedProduct.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedProduct.setCreatorUsername(savedProduct.getCreatorUsername());
        unsavedProduct.setCreatedTime(savedProduct.getCreatedTime());

        return new ResponseEntity<>(save(unsavedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        Product savedProduct = productService.getOne(id);

        Assert.notNull(savedProduct);
        Assert.isTrue(user.getUsername().equals(savedProduct.getCreatorUsername()), "You are not the owner of this product");
        Assert.isTrue(!productService.hasChildProducts(savedProduct), "This product template already has child product.");

        productService.remove(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Product> patch(
            @PathVariable(value = "id") long id,
            @RequestBody Product unsavedProduct,
            @AuthenticationPrincipal User user) {
        Assert.isTrue(unsavedProduct.getRemark() != null && !unsavedProduct.getRemark().trim().isEmpty(), "Remark should not be empty.");

        Product savedProduct = productService.getOne(id);

        Assert.notNull(savedProduct, "Product not found");
        Assert.isTrue(savedProduct.getProductTemplateId() != 0L, "Product template shouldn't be reviewed.");
        ProductStatusTransfer.checkAuthority(user, savedProduct.getStatus().intValue(), unsavedProduct.getStatus().intValue());

        return new ResponseEntity<>(productService.review(user, savedProduct, unsavedProduct), HttpStatus.OK);
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
            Product productTemplate = productService.getOne(productTemplateId);

            Assert.notNull(productTemplate, "Product template not found.");

            Assert.isTrue(product.getMinAvailableRate() >= productTemplate.getMinAvailableRate(), "Product's min available rate should be greater than product template's min available rate.");
            Assert.isTrue(product.getMaxAvailableRate() <= productTemplate.getMaxAvailableRate(), "Product's max available rate should be less than product template's max available rate.");
            Assert.isTrue(StringUtil.containsAll(productTemplate.getAvailableTerms(), product.getAvailableTerms()), "Product's terms are unavailable.");
            Assert.isTrue(product.getProvinceId() > 0L, "Product's province id should be assigned.");
            Assert.isTrue(product.getCityId() > 0L, "Product's city id should be assigned.");
        }

        return productService.save(product);
    }
}
