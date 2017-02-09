package cheanxin.service;

import cheanxin.domain.LoanApplicant;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanApplicantService {
    LoanApplicant save(LoanApplicant unsavedLoanApplicant);
    LoanApplicant findOne(long id);
}
