package cheanxin.service.impl;

import cheanxin.data.LoanRepository;
import cheanxin.domain.Loan;
import cheanxin.domain.LoanLog;
import cheanxin.domain.Product;
import cheanxin.domain.User;
import cheanxin.enums.LoanOperation;
import cheanxin.enums.ProductType;
import cheanxin.exceptions.ResourceNotFoundException;
import cheanxin.service.LoanLogService;
import cheanxin.service.LoanService;
import cheanxin.service.ProductService;
import cheanxin.state.LoanState;
import cheanxin.state.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    private ProductService productService;

    private Map<Integer, LoanState> loanStateMap = new HashMap<>();
    private LoanState abortedLoanState = new AbortedLoanState(this);
    private LoanState draftLoanState = new DraftLoanState(this);
    private LoanState firstReviewPendingLoanState = new FirstReviewPendingLoanState(this);
    private LoanState pricingPendingLoanState = new PricingPendingLoanState(this);
    private LoanState secondReviewPendingLoanState = new SecondReviewPendingLoanState(this);
    private LoanState materialsPendingLoanState = new MaterialsPendingLoanState(this);
    private LoanState contractPendingLoanState = new ContractPendingLoanState(this);
    private LoanState schemePendingLoanState = new SchemePendingLoanState(this);
    private LoanState disbursementPendingLoanState = new DisbursementPendingLoanState(this);

    public LoanServiceImpl() {
        loanStateMap.put(0, abortedLoanState);
        loanStateMap.put(1, draftLoanState);
        loanStateMap.put(2, firstReviewPendingLoanState);
        loanStateMap.put(3, pricingPendingLoanState);
        loanStateMap.put(4, secondReviewPendingLoanState);
        loanStateMap.put(5, materialsPendingLoanState);
        loanStateMap.put(6, contractPendingLoanState);
        loanStateMap.put(7, schemePendingLoanState);
        loanStateMap.put(8, disbursementPendingLoanState);
    }

    @Override
    public Loan handle(User user, LoanOperation loanOperation, Loan unsavedLoan) throws Throwable {
        Loan savedLoan = loanRepository.findOne(unsavedLoan.getId());
        if (savedLoan == null || savedLoan.getStatus() == null) {
            return null;
        }
        LoanState loanState = loanStateMap.get(savedLoan.getStatus());
        if (loanState == null) {
            throw new ResourceNotFoundException("Loan", "id", String.valueOf(unsavedLoan.getId()));
        }
        unsavedLoan.setStatus(savedLoan.getStatus());

        String methodName = loanOperation.getMethodName();
        Method method = loanState.getClass().getDeclaredMethod(methodName, User.class, Loan.class, Loan.class);
        try {
            return (Loan) method.invoke(loanState, user, unsavedLoan, savedLoan);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    @Override
    public boolean isLoanLogExists(long id, LoanState fromLoanState, LoanState toLoanState) {
        return loanLogService.isExists(id, fromLoanState.getValue(), toLoanState.getValue());
    }

    @Override
    @Transactional
    public Loan save(Loan unsavedLoan, boolean isLog) {
        Loan savedLoan = loanRepository.save(unsavedLoan);
        if (savedLoan == null) {
            throw new InternalError("Save loan error.");
        }

        if (isLog && unsavedLoan.getStatus() != null) {
            LoanLog loanLog = new LoanLog(
                    savedLoan.getId(),
                    savedLoan.getCreatorUsername(),
                    unsavedLoan.getStatus(),
                    unsavedLoan.getStatus(),
                    savedLoan.getRemark(),
                    savedLoan.getModifiedTime());
            loanLogService.save(loanLog);
        }

        return savedLoan;
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
            ProductType productType = ProductType.valueOf(product.getProductType());
            String productTypeStr = productType == null ? null : productType.getDesc();
            loan.setProductType(productTypeStr);
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
                ProductType productType = ProductType.valueOf(product.getProductType());
                String productTypeStr = productType == null ? null : productType.getDesc();
                loan.setProductType(productTypeStr);
            }
        }
        
        return loanPage;
    }

    @Override
    @Transactional
    public Loan updateStatus(User user, Loan unsavedLoan, Loan savedLoan, boolean isLog) {
        if (savedLoan == null || savedLoan.getStatus() == null) {
            return null;
        }

        savedLoan.setModifiedTime(unsavedLoan.getModifiedTime());
        savedLoan = loanRepository.save(savedLoan);
        if (savedLoan == null) {
            throw new InternalError("Save loan error.");
        }

        // save loan operation log.
        savedLoan.setReviewRemark(unsavedLoan.getReviewRemark());
        if (isLog && savedLoan.getStatus() != null) {
            LoanLog loanLog = new LoanLog(
                    savedLoan.getId(),
                    user.getUsername(),
                    unsavedLoan.getStatus(),
                    savedLoan.getStatus(),
                    savedLoan.getReviewRemark(),
                    savedLoan.getModifiedTime());
            loanLogService.save(loanLog);
        }

        return savedLoan;
    }

    @Override
    public LoanState getAbortedLoanState() {
        return abortedLoanState;
    }

    @Override
    public LoanState getDraftLoanState() {
        return draftLoanState;
    }

    @Override
    public LoanState getFirstReviewPendingLoanState() {
        return firstReviewPendingLoanState;
    }

    @Override
    public LoanState getPricingPendingLoanState() {
        return pricingPendingLoanState;
    }

    @Override
    public LoanState getSecondReviewPendingLoanState() {
        return secondReviewPendingLoanState;
    }

    @Override
    public LoanState getMaterialsPendingLoanState() {
        return materialsPendingLoanState;
    }

    @Override
    public LoanState getContractPendingLoanState() {
        return contractPendingLoanState;
    }

    @Override
    public LoanState getSchemePendingLoanState() {
        return schemePendingLoanState;
    }

    @Override
    public LoanState getDisbursementPendingLoanState() {
        return disbursementPendingLoanState;
    }
}
