package cheanxin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 273cn on 17/02/08.
 */
public abstract class LoanListService<T> {
    abstract public Page<T> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size);

    protected class SearchLoan {
        private String creatorUsername;
        private String sourceFinancialCommissioner;
        private String applicantName;
        private String applicantMobileNumber;
        private long createdTimeFrom;
        private long createdTimeTo;
        private int status;

        public SearchLoan() {}

        public String getCreatorUsername() {
            return creatorUsername;
        }

        public void setCreatorUsername(String creatorUsername) {
            this.creatorUsername = creatorUsername;
        }

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
    public Specification<T> getWhereClause(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status) {
        SearchLoan searchLoan = new SearchLoan();
        searchLoan.setCreatorUsername(creatorUsername);
        searchLoan.setSourceFinancialCommissioner(sourceFinancialCommissioner);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
        searchLoan.setStatus(status);

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchLoan.getCreatorUsername() != null && !searchLoan.getCreatorUsername().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("creatorUsername").as(String.class), searchLoan.getCreatorUsername()));
                }
                if (searchLoan.getCreatedTimeFrom() > 0) {
                    predicate.add(cb.ge(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeFrom()));
                }
                if (searchLoan.getCreatedTimeTo() > 0) {
                    predicate.add(cb.le(root.get("createdTime").as(Long.class), searchLoan.getCreatedTimeTo()));
                }
                if (searchLoan.getSourceFinancialCommissioner() != null && !searchLoan.getSourceFinancialCommissioner().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("sourceFinancialCommissioner").as(String.class), searchLoan.getSourceFinancialCommissioner()));
                }
                if (searchLoan.getApplicantName() != null && !searchLoan.getApplicantName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("applicantName").as(String.class), searchLoan.getApplicantName() + "%"));
                }
                if (searchLoan.getApplicantMobileNumber() != null && !searchLoan.getApplicantMobileNumber().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("applicantMobileNumber").as(String.class), searchLoan.getApplicantMobileNumber()));
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
