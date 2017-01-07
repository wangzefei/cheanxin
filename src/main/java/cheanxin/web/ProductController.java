package cheanxin.web;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.*;
import cheanxin.exceptions.ForbiddenException;
import cheanxin.exceptions.InvalidArgumentException;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.global.Constants;
import cheanxin.service.ProductService;
import cheanxin.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
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
            @Valid @RequestBody Product unsavedProduct,
            Errors errors,
            @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            throw new InvalidArgumentException(errors.getAllErrors().get(0));
        if (unsavedProduct.getStatus().intValue() != ProductStatus.PENDING_REVIEW.value())
            throw new InvalidArgumentException("Product is not in pending review status.");

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
        if (errors.hasErrors())
            throw new InvalidArgumentException(errors.getAllErrors().get(0));
        Product savedProduct = productService.findOne(id);
        if (savedProduct == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));
        if (savedProduct.getStatus().intValue() != ProductStatus.PENDING_REVIEW.value())
            throw new ForbiddenException("Only product of status pending review can be modified.");
        if (!savedProduct.getCreatorUsername().equals(user.getUsername()))
            throw new UnauthorizedException("Current user is not owner of this product.");

        // attributes below can not be modified.
        unsavedProduct.setId(id);
        unsavedProduct.setProductTemplateId(savedProduct.getProductTemplateId());
        unsavedProduct.setStatus(unsavedProduct.getStatus());
        unsavedProduct.setModifiedTime(System.currentTimeMillis() / 1000);
        unsavedProduct.setCreatorUsername(savedProduct.getCreatorUsername());
        unsavedProduct.setCreatedTime(savedProduct.getCreatedTime());

        return new ResponseEntity<>(save(unsavedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable(value = "id") long id,
            @AuthenticationPrincipal User user) {
        Product savedProduct = productService.findOne(id);
        if (savedProduct == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));
        if (!user.getUsername().equals(savedProduct.getCreatorUsername()))
            throw new UnauthorizedException("You are not the owner of this product");
        if (productService.hasChildProducts(savedProduct))
            throw new ForbiddenException("This product template already has child product.");
        productService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Product> updateStatus(
            @PathVariable(value = "id") long id,
            @RequestBody Product unsaveProduct,
            @AuthenticationPrincipal User user) {
        if (unsaveProduct.getRemark() == null || unsaveProduct.getRemark().trim().isEmpty())
            throw new InvalidArgumentException("Remark should not be empty.");

        Product savedProduct = productService.findOne(id);
        if (savedProduct == null)
            throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(id));
        if (savedProduct.getProductTemplateId() == 0L)
            throw new ForbiddenException("Product template shouldn't be reviewed.");
        if (!ProductStatusTransfer.hasAuthority(user, savedProduct.getStatus().intValue(), unsaveProduct.getStatus().intValue()))
            throw new UnauthorizedException(user.getUsername());

        return new ResponseEntity<>(productService.review(user, savedProduct, unsaveProduct), HttpStatus.OK);
    }

    private Product save(Product product) {
        if (!LoanPolicy.contains(product.getLoanPolicy().intValue()))
            throw new InvalidArgumentException("Product's loan policy does not exist.");
        if (!ProductType.contains(product.getProductType().intValue()))
            throw new InvalidArgumentException("Product's product type does not exist.");
        if (!RepaymentMethod.contains(product.getRepaymentMethod().intValue()))
            throw new InvalidArgumentException("Product's repayment method does not exist.");
        if (product.getMinAvailableRate() > product.getMaxAvailableRate())
            throw new InvalidArgumentException("Product's min available rate should be less than max available rate.");

        if (product.getProductTemplateId() == 0L) {
            product.setProvinceId(0L);
            product.setCityId(0L);
        } else {
            long productTemplateId = product.getProductTemplateId().longValue();
            Product productTemplate = productService.findOne(productTemplateId);
            if (productTemplate == null)
                throw new ResourceNotFoundException(Product.class.getSimpleName(), "id", String.valueOf(productTemplateId));
            if (product.getMinAvailableRate() < productTemplate.getMinAvailableRate())
                throw new InvalidArgumentException("Product's min available rate should be greater than product template's min available rate.");
            if (product.getMaxAvailableRate() > productTemplate.getMaxAvailableRate())
                throw new InvalidArgumentException("Product's max available rate should be less than product template's max available rate.");
            if (!StringUtil.containsAll(productTemplate.getAvailableTerms(), product.getAvailableTerms()))
              throw new InvalidArgumentException("Product's terms are unavailable.");
            if (product.getProvinceId() <= 0L)
                throw new InvalidArgumentException("Product's province id should be assigned.");
            if (product.getCityId() <= 0L)
                throw new InvalidArgumentException("Product's city id should be assigned.");
        }

        return productService.save(product);
    }
}
