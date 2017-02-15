package cheanxin.service.impl;

import cheanxin.data.LoanDraftRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanDraftStatusTransfer;
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
    @Transactional
    public LoanDraft save(int fromStatus, LoanDraft toLoanDraft) {
        LoanDraft savedLoanDraft = loanDraftRepository.save(toLoanDraft);

        if (!toLoanDraft.getStatus().equals(fromStatus)) {
            LoanDraftStatusTransfer loanDraftStatusTransfer = LoanDraftStatusTransfer.valueOf(fromStatus, toLoanDraft.getStatus());
            LoanLog loanLog = new LoanLog(savedLoanDraft.getId(),
                    savedLoanDraft.getCreatorUsername(),
                    loanDraftStatusTransfer.getValue(),
                    savedLoanDraft.getRemark(),
                    savedLoanDraft.getModifiedTime());
            loanLogService.save(loanLog);
        }

        return savedLoanDraft;
    }

    @Override
    public LoanDraft getOne(long id) {
        return loanDraftRepository.findOne(id);
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
    public boolean isExists(long id) {
        return this.getOne(id) != null;
    }
}
