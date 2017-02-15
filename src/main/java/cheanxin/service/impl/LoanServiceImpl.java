package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanStatus;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.service.LoanDraftService;
import cheanxin.service.LoanListService;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 273cn on 17/02/08.
 */
@Service
public class LoanServiceImpl extends LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanLogService loanLogService;

    @Autowired
    LoanDraftService loanDraftService;

    @Override
    @Transactional
    public Loan save(Loan unsavedLoan, LoanDraft loanDraft) {
        Loan savedLoan = loanRepository.save(unsavedLoan);

        if (loanDraft != null)
            loanDraftService.save(LoanDraftStatus.SECOND_DRAFT.value(), loanDraft);

        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                savedLoan.getLoanDraftId(),
                savedLoan.getCreatorUsername(),
                LoanStatusTransfer.FIRST_REVIEW_ACCEPTED_TO_FIRST_REVIEW_ACCEPTED.getValue(),
                savedLoan.getRemark(),
                savedLoan.getModifiedTime());
        loanLogService.save(loanLog);

        return loanRepository.save(unsavedLoan);
    }

    @Override
    public Loan getOne(long id) {
        return loanRepository.findOne(id);
    }

    @Override
    public Page<Loan> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchLoan searchLoan = new SearchLoan();
        searchLoan.setCreatorUsername(creatorUsername);
        searchLoan.setSourceFinancialCommissioner(sourceFinancialCommissioner);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
        searchLoan.setStatus(status);
        Specification<Loan> specification = this.getWhereClause(searchLoan);
        return loanRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Loan updateStatus(User user, int fromStatus, Loan unsavedLoan) {
        // save from status before save loan
        int toStatus = unsavedLoan.getStatus().intValue();

        Loan savedLoan = save(unsavedLoan);

        // save loan operation log.
        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                user.getUsername(),
                LoanStatusTransfer.valueOf(fromStatus, savedLoan.getStatus()).getValue(),
                unsavedLoan.getRemark(),
                System.currentTimeMillis() / 1000);
        loanLogService.save(loanLog);

        return savedLoan;
    }

    private Loan save(Loan unsavedLoan) {
        return save(unsavedLoan, null);
    }
}
