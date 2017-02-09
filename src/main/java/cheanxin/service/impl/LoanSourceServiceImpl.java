package cheanxin.service.impl;

import cheanxin.data.LoanSourceRepository;
import cheanxin.domain.LoanSource;
import cheanxin.service.LoanSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanSourceServiceImpl implements LoanSourceService {
    @Autowired
    LoanSourceRepository loanSourceRepository;

    @Override
    public LoanSource save(LoanSource unsavedLoanSource) {
        return loanSourceRepository.save(unsavedLoanSource);
    }

    @Override
    public LoanSource findOne(long id) {
        return loanSourceRepository.getOne(id);
    }
}
