package cheanxin.service;

import cheanxin.domain.Loan;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanService {
    Loan save(Loan unsavedLoan);
    Loan findOne(long id);
}
