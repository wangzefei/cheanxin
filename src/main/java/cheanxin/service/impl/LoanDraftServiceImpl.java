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
    private LoanSourceService loanSourceService;

    @Autowired
    private LoanApplicantService loanApplicantService;

    @Autowired
    private LoanCoApplicantService loanCoApplicantService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanGuarantorService loanGuarantorService;

    @Autowired
    private LoanLogService loanLogService;
    
    @Autowired
    private LoanVehicleService loanVehicleService;
    
    @Override
    public LoanDraft save(LoanDraft unsavedLoanDraft) {
        return loanDraftRepository.save(unsavedLoanDraft);
    }

    @Override
    public LoanDraft findOne(long id) {
        return loanDraftRepository.getOne(id);
    }

    @Override
    public Page<LoanDraft> getLoanDrafts(String creatorUsername, int page, int size) {
        Pageable pageRequest = new PageRequest(page, size);
        if (creatorUsername == null) {
            return loanDraftRepository.findAll(pageRequest);
        }
        return loanDraftRepository.findByCreatorUsername(creatorUsername, pageRequest);
    }

    @Override
    public void delete(long id) {
        loanDraftRepository.delete(id);
    }

    @Override
    @Transactional
    public void transferToLoan(User user, LoanDraft loanDraft) {
        LoanSource savedLoanSource = loanSourceService.save(new LoanSource(loanDraft));
        LoanApplicant savedLoanApplicant = loanApplicantService.save(new LoanApplicant(loanDraft));
        LoanCoApplicant savedLoanCoApplicant = loanCoApplicantService.save(new LoanCoApplicant(loanDraft));
        LoanVehicle savedLoanVehicle = loanVehicleService.save(new LoanVehicle(loanDraft));
        LoanGuarantor savedLoanGuarantor = loanGuarantorService.save(new LoanGuarantor(loanDraft));

        long now = System.currentTimeMillis() / 1000;
        Loan unsavedLoan = new Loan(loanDraft);
        unsavedLoan.setExtApplicantId(savedLoanApplicant.getId());
        unsavedLoan.setExtCoApplicantId(savedLoanCoApplicant.getId());
        unsavedLoan.setExtSourceId(savedLoanSource.getId());
        unsavedLoan.setExtGuarantorId(savedLoanGuarantor.getId());
        unsavedLoan.setExtVehicleId(savedLoanVehicle.getId());
        unsavedLoan.setStatus(LoanStatus.FIRST_REVIEW_PENDING.value());
        unsavedLoan.setCreatedTime(now);
        Loan savedLoan = loanService.save(unsavedLoan);

        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                user.getUsername(),
                LoanStatusTransfer.FIRST_REVIEW_PENDING_TO_FIRST_REVIEW_PENDING.getValue(),
                savedLoan.getRemark(),
                now);
        loanLogService.save(loanLog);

        delete(loanDraft.getId());
    }
}
