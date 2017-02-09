package cheanxin.service.impl;

import cheanxin.data.LoanGuarantorRepository;
import cheanxin.domain.LoanGuarantor;
import cheanxin.service.LoanGuarantorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanGuarantorServiceImpl implements LoanGuarantorService {
    @Autowired
    LoanGuarantorRepository loanGuarantorRepository;

    @Override
    public LoanGuarantor save(LoanGuarantor unsavedLoanGuarantor) {
        return loanGuarantorRepository.save(unsavedLoanGuarantor);
    }

    @Override
    public LoanGuarantor findOne(long id) {
        return loanGuarantorRepository.getOne(id);
    }
}
