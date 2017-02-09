package cheanxin.service;

import cheanxin.domain.LoanGuarantor;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanGuarantorService {
    LoanGuarantor save(LoanGuarantor unsavedLoanGuarantor );
    LoanGuarantor findOne(long id);
}
