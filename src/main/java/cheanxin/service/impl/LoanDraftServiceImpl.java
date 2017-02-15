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
public class LoanDraftServiceImpl extends LoanDraftService {
    @Autowired
    private LoanDraftRepository loanDraftRepository;

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
    public Page<LoanDraft> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchLoan searchLoan = new SearchLoan();
        searchLoan.setCreatorUsername(creatorUsername);
        searchLoan.setSourceFinancialCommissioner(sourceFinancialCommissioner);
        searchLoan.setApplicantName(applicantName);
        searchLoan.setApplicantMobileNumber(applicantMobileNumber);
        searchLoan.setCreatedTimeFrom(createdTimeFrom);
        searchLoan.setCreatedTimeTo(createdTimeTo);
        searchLoan.setStatus(status);
        Specification<LoanDraft> specification = this.getWhereClause(searchLoan);
        return loanDraftRepository.findAll(specification, pageable);
    }

    @Override
    public void remove(long id) {
        loanDraftRepository.delete(id);
    }
}
