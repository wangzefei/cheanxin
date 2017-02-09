package cheanxin.data;

import cheanxin.domain.LoanApplicant;
import cheanxin.domain.LoanCoApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/02/08.
 */
@Repository
public interface LoanCoApplicantRepository extends JpaRepository<LoanCoApplicant, Long> {
}
