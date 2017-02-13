package cheanxin.service;

import cheanxin.domain.LoanDraft;
import cheanxin.domain.User;
import org.springframework.data.domain.Page;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanDraftService {
    LoanDraft save(LoanDraft unsavedLoanDraft);
    LoanDraft getOne(long id);
    Page<LoanDraft> list(String creatorUsername, int page, int size);
    void remove(long id);
    void transferToLoan(User User, LoanDraft loanDraft);
}
