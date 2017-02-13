package cheanxin.service.impl;

import cheanxin.data.LoanDraftRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanStatus;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanDraftServiceImpl implements LoanDraftService {
    @Autowired
    private LoanDraftRepository loanDraftRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanLogService loanLogService;
    
    @Override
    public LoanDraft save(LoanDraft unsavedLoanDraft) {
        return loanDraftRepository.save(unsavedLoanDraft);
    }

    @Override
    public LoanDraft getOne(long id) {
        return loanDraftRepository.getOne(id);
    }

    @Override
    public Page<LoanDraft> list(String creatorUsername, int page, int size) {
        Pageable pageRequest = new PageRequest(page, size);
        if (creatorUsername == null) {
            return loanDraftRepository.findAll(pageRequest);
        }
        return loanDraftRepository.findByCreatorUsername(creatorUsername, pageRequest);
    }

    @Override
    public void remove(long id) {
        loanDraftRepository.delete(id);
    }

    @Override
    @Transactional
    public void transferToLoan(User user, LoanDraft loanDraft) {
        Loan unsavedLoan = new Loan(loanDraft);
        unsavedLoan.setStatus(LoanStatus.FIRST_REVIEW_PENDING.value());
        unsavedLoan.setCreatedTime(loanDraft.getModifiedTime());
        unsavedLoan.setCreatorUsername(user.getUsername());
        Loan savedLoan = loanService.save(unsavedLoan);

        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                user.getUsername(),
                LoanStatusTransfer.FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_PENDING.getValue(),
                savedLoan.getRemark(),
                loanDraft.getModifiedTime());
        loanLogService.save(loanLog);

        remove(loanDraft.getId());
    }
}
