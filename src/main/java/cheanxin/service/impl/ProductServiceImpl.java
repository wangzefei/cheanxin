package cheanxin.service.impl;

import cheanxin.data.ProductRepository;
import cheanxin.domain.Product;
import cheanxin.domain.ProductLog;
import cheanxin.domain.User;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.service.ProductLogService;
import cheanxin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/01/07.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLogService productLogService;

    @Override
    public Product save(Product unsavedProduct) {
        return productRepository.save(unsavedProduct);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public Product findOne(long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Page<Product> getProducts(String username, long productTemplateId, String name, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        if (name == null || name.trim().isEmpty()) name = "";

        if (username == null) {
            if (productTemplateId < 0)
                return productRepository.findByNameIgnoreCaseContainingAndStatus(name, status, pageable);
            return productRepository.findByProductTemplateIdAndNameIgnoreCaseContainingAndStatus(productTemplateId, name, status, pageable);
        }

        if (productTemplateId < 0)
            return productRepository.findByCreatorUsernameAndNameIgnoreCaseContainingAndStatus(username, name, status, pageable);
        return productRepository.findByCreatorUsernameAndProductTemplateIdAndNameIgnoreCaseContainingAndStatus(username, productTemplateId, name, status, pageable);
    }

    @Override
    public Page<Product> getProducts(long productTemplateId, String name, int status, int page, int size) {
        return getProducts(null, productTemplateId, name, status, page, size);
    }

    @Override
    public Product review(User user, Product fromProduct, Product toProduct) {
        // save from status before save product.
        int fromStatus = fromProduct.getStatus().intValue();
        int toStatus = toProduct.getStatus().intValue();
        fromProduct.setStatus(toStatus);
        Product savedProduct = save(fromProduct);

        // save product operation log.
        ProductLog productLog = new ProductLog(
                savedProduct.getId(),
                user.getUsername(),
                ProductStatusTransfer.valueOf(fromStatus, toStatus).getValue(),
                toProduct.getRemark(),
                System.currentTimeMillis() / 1000);
        productLogService.save(productLog);

        return savedProduct;
    }

    @Override
    public boolean hasChildProducts(Product product) {
        if (product.getProductTemplateId() != 0L)
            return false;
        return productRepository.findByProductTemplateId(product.getId(), new PageRequest(0, 1)).hasContent();
    }
}
