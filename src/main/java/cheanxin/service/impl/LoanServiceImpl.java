package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.*;
import cheanxin.enums.LoanDraftStatus;
import cheanxin.enums.LoanStatusTransfer;
import cheanxin.enums.ProductType;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanDraftService;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import cheanxin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public Loan save(Loan unsavedLoan, LoanDraft loanDraft) {
        if (loanDraft != null) {
            loanDraftService.save(LoanDraftStatus.SECOND_DRAFT.value(), loanDraft);
        }

        Loan savedLoan = loanRepository.save(unsavedLoan);
        if (savedLoan == null) {
            throw new InternalError("Save loan error.");
        }

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
        Loan loan = loanRepository.findOne(id);
        if (loan == null || loan.getProductId() == null) {
            return loan;
        }

        // product name and product type
        Product product = productService.getOne(loan.getProductId());
        if (product != null) {
            loan.setProductName(product.getName());
            loan.setProductType(ProductType.valueOf(product.getProductType().intValue()).getDesc());
        }

        return loan;
    }

    @Override
    public Page<Loan> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Specification<Loan> specification = this.getWhereClause(creatorUsername, sourceFinancialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status);
        Page<Loan> loanPage = loanRepository.findAll(specification, pageable);
        if (loanPage == null) {
            return null;
        }

        // product name
        Map<Long, Product> productMap = new HashMap<>();
        for (Loan loan : loanPage) {
            if (loan == null || loan.getProductId() == null) {
                continue;
            }
            productMap.put(loan.getProductId(), null);
        }
        List<Product> productList = productService.list(productMap.keySet());
        if (productList != null) {
            for (Product product : productList) {
                if (product == null || product.getId() == null) {
                    continue;
                }
                productMap.put(product.getId(), product);
            }
            for (Loan loan : loanPage) {
                if (loan == null || loan.getProductId() == null) {
                    continue;
                }
                Product product = productMap.get(loan.getProductId());
                if (product == null) {
                    continue;
                }
                loan.setProductName(product.getName());
            }
        }
        
        return loanPage;
    }

    @Override
    @Transactional
    public Loan updateStatus(User user, int fromStatus, Loan unsavedLoan) {
        if (unsavedLoan == null || unsavedLoan.getStatus() == null) {
            return null;
        }

        LoanStatusTransfer loanStatusTransfer = LoanStatusTransfer.valueOf(fromStatus, unsavedLoan.getStatus());
        if (loanStatusTransfer == null) {
            throw new UnauthorizedException("Loan status transfer undefined.");
        }

        Loan savedLoan = save(unsavedLoan);

        // save loan operation log.
        LoanLog loanLog = new LoanLog(
                savedLoan.getId(),
                user.getUsername(),
                loanStatusTransfer.getValue(),
                unsavedLoan.getRemark(),
                System.currentTimeMillis() / 1000);
        loanLogService.save(loanLog);

        return savedLoan;
    }

    private Loan save(Loan unsavedLoan) {
        return save(unsavedLoan, null);
    }
}
