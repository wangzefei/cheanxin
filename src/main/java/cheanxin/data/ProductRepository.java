package cheanxin.data;

import cheanxin.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 273cn on 16/12/14.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByProductTemplateIdAndNameIgnoreCaseContainingAndStatus(long productTemplateId, String name, int status, Pageable pageable);
    Page<Product> findByNameIgnoreCaseContainingAndStatus(String name, int status, Pageable pageable);
    Page<Product> findByCreatorUsernameAndProductTemplateIdAndNameIgnoreCaseContainingAndStatus(String username, long productTemplateId, String name, int status, Pageable pageable);
    Page<Product> findByCreatorUsernameAndNameIgnoreCaseContainingAndStatus(String username, String name, int status, Pageable pageable);
    Page<Product> findByProductTemplateId(long productTemplateId, Pageable pageable);
}
