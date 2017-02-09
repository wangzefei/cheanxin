package cheanxin.service.impl;

import cheanxin.data.LoanApplicantRepository;
import cheanxin.domain.LoanApplicant;
import cheanxin.service.LoanApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanApplicantServiceImpl implements LoanApplicantService {
    @Autowired
    LoanApplicantRepository loanApplicantRepository;

    @Override
    public LoanApplicant save(LoanApplicant unsavedLoanApplicant) {
        return loanApplicantRepository.save(unsavedLoanApplicant);
    }

    @Override
    public LoanApplicant findOne(long id) {
        return loanApplicantRepository.getOne(id);
    }
}
