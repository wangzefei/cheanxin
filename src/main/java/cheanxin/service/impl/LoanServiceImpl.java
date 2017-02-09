package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.Loan;
import cheanxin.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Override
    public Loan save(Loan unsavedLoan) {
        return loanRepository.save(unsavedLoan);
    }

    @Override
    public Loan findOne(long id) {
        return loanRepository.getOne(id);
    }
}
