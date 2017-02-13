package cheanxin.service;

import cheanxin.domain.Product;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/01/07.
 */
public interface ProductService {
    Product save(Product unsavedProduct);
    void remove(long id);
    Product getOne(long id);
    Page<Product> list(long productTemplateId, String name, int status, int page, int size);
    Page<Product> list(String username, long productTemplateId, String name, int status, int page, int size);
    Product review(User user, Product fromProduct, Product toProduct);
    boolean hasChildProducts(Product product);
}
