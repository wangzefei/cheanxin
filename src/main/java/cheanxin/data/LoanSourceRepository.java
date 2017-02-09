package cheanxin.data;

import cheanxin.domain.LoanSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/02/08.
 */
@Repository
public interface LoanSourceRepository extends JpaRepository<LoanSource, Long> {
}
