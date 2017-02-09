package cheanxin.service.impl;

import cheanxin.data.LoanCoApplicantRepository;
import cheanxin.domain.LoanCoApplicant;
import cheanxin.service.LoanCoApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanCoApplicantServiceImpl implements LoanCoApplicantService {
    @Autowired
    LoanCoApplicantRepository loanCoApplicantRepository;

    @Override
    public LoanCoApplicant save(LoanCoApplicant unsavedLoanCoApplicant) {
        return loanCoApplicantRepository.save(unsavedLoanCoApplicant);
    }

    @Override
    public LoanCoApplicant findOne(long id) {
        return loanCoApplicantRepository.getOne(id);
    }
}
