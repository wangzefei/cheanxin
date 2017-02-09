package cheanxin.service;

import cheanxin.domain.LoanSource;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanSourceService {
    LoanSource save(LoanSource unsavedLoanSource);
    LoanSource findOne(long id);
}
