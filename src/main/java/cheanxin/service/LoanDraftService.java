package cheanxin.service;

import cheanxin.domain.LoanDraft;

/**
 * Created by 273cn on 17/02/08.
 */
public abstract class LoanDraftService extends LoanListService<LoanDraft> {
    public abstract LoanDraft save(int fromStatus, LoanDraft toLoanDraft);
    public abstract LoanDraft getOne(long id);
    public abstract void remove(long id);
}
