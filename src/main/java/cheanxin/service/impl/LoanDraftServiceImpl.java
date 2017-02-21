package cheanxin.service.impl;

import cheanxin.data.LoanDraftRepository;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.LoanLog;
import cheanxin.domain.Product;
import cheanxin.enums.LoanDraftStatusTransfer;
import cheanxin.enums.ProductType;
import cheanxin.exceptions.UnauthorizedException;
import cheanxin.service.LoanDraftService;
import cheanxin.service.LoanLogService;
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
public class LoanDraftServiceImpl extends LoanDraftService {
    @Autowired
    private LoanDraftRepository loanDraftRepository;

    @Autowired
    private LoanLogService loanLogService;

    @Autowired
    private ProductService productService;
    
    @Override
    @Transactional
    public LoanDraft save(int fromStatus, LoanDraft toLoanDraft) {
        LoanDraft savedLoanDraft = loanDraftRepository.save(toLoanDraft);
        if (savedLoanDraft == null) {
            throw new InternalError("Save loan draft error.");
        }

        if (!toLoanDraft.getStatus().equals(fromStatus)) {
            LoanDraftStatusTransfer loanDraftStatusTransfer = LoanDraftStatusTransfer.valueOf(fromStatus, toLoanDraft.getStatus());
            if (loanDraftStatusTransfer == null) {
                throw new UnauthorizedException("Undefined state transfer.");
            }
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
        LoanDraft loanDraft = loanDraftRepository.findOne(id);
        if (loanDraft == null || loanDraft.getProductId() == null) {
            return loanDraft;
        }

        // set product name and product type
        Product product = productService.getOne(loanDraft.getProductId());
        if (product != null) {
            loanDraft.setProductName(product.getName());
            Integer productTypeValue = product.getProductType();
            if (productTypeValue != null) {
                ProductType productTypeEnum = ProductType.valueOf(productTypeValue);
                if (productTypeEnum != null) {
                    loanDraft.setProductType(productTypeEnum.getDesc());
                }
            }
        }

        return loanDraft;
    }

    @Override
    public Page<LoanDraft> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Specification<LoanDraft> specification = this.getWhereClause(creatorUsername, sourceFinancialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status);
        Page<LoanDraft> loanDraftPage = loanDraftRepository.findAll(specification, pageable);
        if (loanDraftPage == null) {
            return null;
        }

        // set product name
        Map<Long, Product> productMap = new HashMap<>();
        for (LoanDraft loanDraft : loanDraftPage) {
            if (loanDraft == null || loanDraft.getProductId() == null) {
                continue;
            }
            productMap.put(loanDraft.getProductId(), null);
        }
        List<Product> productList = productService.list(productMap.keySet());
        if (productList != null) {
            for (Product product : productList) {
                if (product == null) {
                    continue;
                }
                productMap.put(product.getId(), product);
            }
            for (LoanDraft loanDraft : loanDraftPage) {
                if (loanDraft == null || loanDraft.getProductId() == null) {
                    continue;
                }
                Product product = productMap.get(loanDraft.getProductId());
                if (product == null) {
                    continue;
                }
                loanDraft.setProductName(product.getName());
            }
        }
        
        return loanDraftPage;
    }

    @Override
    public void remove(long id) {
        loanDraftRepository.delete(id);
    }
}
