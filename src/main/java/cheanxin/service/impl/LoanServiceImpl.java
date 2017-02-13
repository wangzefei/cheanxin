package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.enums.ProductStatusTransfer;
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

    @Override
    public Loan save(Loan unsavedLoan) {
        return loanRepository.save(unsavedLoan);
    }

    @Override
    public Loan getOne(long id) {
        return loanRepository.getOne(id);
    }

    @Override
    public Page<Loan> list(String financialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        LoanServiceImpl.SearchLoan searchLoan = new LoanServiceImpl.SearchLoan();
        searchLoan.setFinancialCommissioner(financialCommissioner);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
        Specification<Loan> specification = this.getWhereClause(searchLoan);
        return loanRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Loan updateStatus(User user, Loan fromLoan, Loan toLoan) {
        // save from status before save loan.
        int fromStatus = fromLoan.getStatus().intValue();
        int toStatus = toLoan.getStatus().intValue();

        LoanStatusTransfer loanStatusTransfer = LoanStatusTransfer.valueOf(fromStatus, toStatus);

        if (loanStatusTransfer == LoanStatusTransfer.FIRST_REVIEW_REJECTED_TO_FIRST_REVIEW_PENDING) {
            fromLoan = new Loan(toLoan);
        }

        if (loanStatusTransfer == LoanStatusTransfer.SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING) {
            fromLoan = new Loan(toLoan);
        }

        if (loanStatusTransfer == LoanStatusTransfer.CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING) {
            fromLoan.setProductId(toLoan.getProductId());
            fromLoan.setLoanRate(toLoan.getLoanRate());
            fromLoan.setLoanTerms(toLoan.getLoanTerms());
            fromLoan.setLoanMonthlyInterestRate(toLoan.getLoanMonthlyInterestRate());
            fromLoan.setPrepaymentPenaltyRate(toLoan.getPrepaymentPenaltyRate());
        }

        fromLoan.setStatus(toStatus);
        Loan savedLoan = save(fromLoan);

        // save loan operation log.
        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                user.getUsername(),
                loanStatusTransfer.getValue(),
                toLoan.getRemark(),
                System.currentTimeMillis() / 1000);
        loanLogService.save(loanLog);

        return savedLoan;
    }

    private class SearchLoan {
        private String financialCommissioner;
        private String applicantName;
        private String applicantMobileNumber;
        private long createdTimeFrom;
        private long createdTimeTo;
        private int status;

        public SearchLoan() {}

        public String getFinancialCommissioner() {
            return financialCommissioner;
        }

        public void setFinancialCommissioner(String financialCommissioner) {
            this.financialCommissioner = financialCommissioner;
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
                if (searchLoan.getFinancialCommissioner() != null && !searchLoan.getFinancialCommissioner().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("financialCommissioner").as(String.class), searchLoan.getFinancialCommissioner()));
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
}
