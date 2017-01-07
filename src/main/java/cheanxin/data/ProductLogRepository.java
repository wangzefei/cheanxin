package cheanxin.data;

import cheanxin.domain.ProductLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 273cn on 16/12/14.
 */
@Repository
public interface ProductLogRepository extends JpaRepository<ProductLog, Long> {
}
