package cheanxin.service;

import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanDraftService {
    LoanDraft save(LoanDraft unsavedLoanDraft);
    LoanDraft findOne(long id);
    Page<LoanDraft> getLoanDrafts(String creatorUsername, int page, int size);
    void delete(long id);
    void transferToLoan(User User, LoanDraft loanDraft);
}
