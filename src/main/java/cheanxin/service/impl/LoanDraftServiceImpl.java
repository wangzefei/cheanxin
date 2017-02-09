package cheanxin.service.impl;

import cheanxin.domain.Loan;
import cheanxin.domain.LoanDraft;
import cheanxin.service.LoanDraftService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanDraftServiceImpl implements LoanDraftService {
    @Override
    public LoanDraft save(LoanDraft unsavedLoanDraft) {
        return null;
    }

    @Override
    public LoanDraft findOne(long id) {
        return null;
    }

    @Override
    public Page<LoanDraft> getLoanDrafts(String creatorUsername, int page, int size) {
        return null;
    }
}
