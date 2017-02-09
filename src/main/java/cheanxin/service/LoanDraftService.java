package cheanxin.service;

import cheanxin.domain.LoanDraft;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanDraftService {
    LoanDraft save(LoanDraft unsavedLoanDraft);
    LoanDraft findOne(long id);
    Page<LoanDraft> getLoanDrafts(String creatorUsername, int page, int size);
}
