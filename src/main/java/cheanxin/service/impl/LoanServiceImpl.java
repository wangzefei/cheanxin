package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanStatus;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.service.LoanDraftService;
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
public class LoanServiceImpl implements LoanService {
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
    public Page<Loan> list(String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        LoanServiceImpl.SearchLoan searchLoan = new LoanServiceImpl.SearchLoan();
        searchLoan.setSourceFinancialCommissioner(sourceFinancialCommissioner);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
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

    private class SearchLoan {
        private String sourceFinancialCommissioner;
        private String applicantName;
        private String applicantMobileNumber;
        private long createdTimeFrom;
        private long createdTimeTo;
        private int status;

        public SearchLoan() {}

        public String getSourceFinancialCommissioner() {
            return sourceFinancialCommissioner;
        }

        public void setSourceFinancialCommissioner(String sourceFinancialCommissioner) {
            this.sourceFinancialCommissioner = sourceFinancialCommissioner;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplicantMobileNumber() {
            return applicantMobileNumber;
        }

        public void setApplicantMobileNumber(String applicantMobileNumber) {
            this.applicantMobileNumber = applicantMobileNumber;
        }

        public long getCreatedTimeFrom() {
            return createdTimeFrom;
        }

        public void setCreatedTimeFrom(long createdTimeFrom) {
            this.createdTimeFrom = createdTimeFrom;
        }

        public long getCreatedTimeTo() {
            return createdTimeTo;
        }

        public void setCreatedTimeTo(long createdTimeTo) {
            this.createdTimeTo = createdTimeTo;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    /**
     * generate where clause dynamically.
     * @param searchLoan
     * @return
     */
    private Specification<Loan> getWhereClause(final LoanServiceImpl.SearchLoan searchLoan){
        return new Specification<Loan>() {
            @Override
            public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchLoan.getSourceFinancialCommissioner() != null && !searchLoan.getSourceFinancialCommissioner().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("financialCommissioner").as(String.class), searchLoan.getSourceFinancialCommissioner()));
                }
                if (searchLoan.getApplicantMobileNumber() != null && !searchLoan.getApplicantMobileNumber().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("applicantMobileNumber").as(String.class), searchLoan.getApplicantMobileNumber()));
                }
                if (searchLoan.getApplicantName() != null && !searchLoan.getApplicantName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("applicantName").as(String.class), searchLoan.getApplicantName() + "%"));
                }
                if (searchLoan.getCreatedTimeFrom() > 0) {
                    predicate.add(cb.ge(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeFrom()));
                }
                if (searchLoan.getCreatedTimeTo() > 0) {
                    predicate.add(cb.le(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeTo()));
                }
                if (searchLoan.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("status").as(Integer.class), searchLoan.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    private Loan save(Loan unsavedLoan) {
        return save(unsavedLoan, null);
    }
}
