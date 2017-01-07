package cheanxin.service;

import cheanxin.domain.Product;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/01/07.
 */
public interface ProductService {
    Product save(Product unsavedProduct);
    void delete(long id);
    Product findOne(long id);
    Page<Product> getProducts(long productTemplateId, String name, int status, int page, int size);
    Page<Product> getProducts(String username, long productTemplateId, String name, int status, int page, int size);
    boolean isExists(long id);
    Product review(Product product, int toStatus);
}
