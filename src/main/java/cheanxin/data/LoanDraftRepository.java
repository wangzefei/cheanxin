package cheanxin.data;

import cheanxin.domain.LoanDraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jawinton on 17/02/08.
 */
@Repository
public interface LoanDraftRepository extends JpaRepository<LoanDraft, Long> {
    Page<LoanDraft> findByCreatorUsername(String creatorUsername, Pageable pageable);
}
