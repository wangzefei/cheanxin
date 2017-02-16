package cheanxin.data;

import cheanxin.domain.LoanLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/02/08.
 */
@Repository
public interface LoanLogRepository extends JpaRepository<LoanLog, Long> {
    Page<LoanLog> findByLoanIdAndOperatorType(long loanId, int operatorType, Pageable pageable);
}
