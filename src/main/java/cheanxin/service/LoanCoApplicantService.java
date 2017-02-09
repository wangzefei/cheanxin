package cheanxin.service;

import cheanxin.domain.LoanCoApplicant;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanCoApplicantService {
    LoanCoApplicant save(LoanCoApplicant unsavedLoanCoApplicant);
    LoanCoApplicant findOne(long id);
}
