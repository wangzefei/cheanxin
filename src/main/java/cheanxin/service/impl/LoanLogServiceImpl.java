package cheanxin.service.impl;

import cheanxin.data.LoanLogRepository;
import cheanxin.domain.LoanLog;
import cheanxin.service.LoanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanLogServiceImpl implements LoanLogService {
    @Autowired
    LoanLogRepository loanLogRepository;

    @Override
    public LoanLog save(LoanLog unsavedLoanLog) {
        return loanLogRepository.save(unsavedLoanLog);
    }

    @Override
    public boolean isExists(long loanId, int fromStatus, int toStatus) {
        return loanLogRepository.findByLoanIdAndFromStatusAndToStatus(loanId, fromStatus, toStatus, new PageRequest(0, 1)).hasContent();
    }
}
