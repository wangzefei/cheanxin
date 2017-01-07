package cheanxin.service.impl;

import cheanxin.data.ProductRepository;
import cheanxin.domain.Product;
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
    public boolean isExists(long id) {
        return findOne(id) != null;
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
            return productRepository.findByUsernameAndNameIgnoreCaseContainingAndStatus(username, name, status, pageable);
        return productRepository.findByUsernameAndProductTemplateIdAndNameIgnoreCaseContainingAndStatus(username, productTemplateId, name, status, pageable);
    }

    @Override
    public Page<Product> getProducts(long productTemplateId, String name, int status, int page, int size) {
        return getProducts(null, productTemplateId, name, status, page, size);
    }

    @Override
    public Product review(Product product, int toStatus) {
        product.setStatus(toStatus);
        Product savedProduct = save(product);

        // TODO: save product operation log.

        return savedProduct;
    }
}
