package cheanxin.service;

import cheanxin.domain.LoanLog;
import cheanxin.enums.LoanStatusTransfer;

/**
 * Created by 273cn on 17/02/08.
 */
public interface LoanLogService {
    LoanLog save(LoanLog unsavedLoanLog);
    boolean isExists(long loanId, LoanStatusTransfer loanStatusTransfer);
}
