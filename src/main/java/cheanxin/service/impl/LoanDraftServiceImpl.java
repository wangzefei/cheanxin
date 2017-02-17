package cheanxin.service.impl;

import cheanxin.data.LoanDraftRepository;
import cheanxin.domain.LoanDraft;
import cheanxin.domain.LoanLog;
import cheanxin.domain.Product;
import cheanxin.enums.LoanDraftStatusTransfer;
import cheanxin.enums.ProductType;
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
        LoanDraft loanDraft = loanDraftRepository.findOne(id);
        if (loanDraft == null || loanDraft.getProductId() == null) {
            return loanDraft;
        }

        // product name and product type
        Product product = productService.getOne(loanDraft.getProductId());
        if (product != null) {
            loanDraft.setProductName(product.getName());
            loanDraft.setProductType(ProductType.valueOf(product.getProductType().intValue()).getDesc());
        }

        return loanDraft;
    }

    @Override
    public Page<LoanDraft> list(String creatorUsername, String sourceFinancialCommissioner, String applicantName, String applicantMobileNumber, long createdTimeFrom, long createdTimeTo, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Specification<LoanDraft> specification = this.getWhereClause(creatorUsername, sourceFinancialCommissioner, applicantName, applicantMobileNumber, createdTimeFrom, createdTimeTo, status);
        Page<LoanDraft> loanDraftPage = loanDraftRepository.findAll(specification, pageable);

        // product name
        Map<Long, Product> productMap = new HashMap<>();
        for (LoanDraft loanDraft : loanDraftPage) {
            productMap.put(loanDraft.getProductId(), null);
        }
        List<Product> productList = productService.list(productMap.keySet());
        for (Product product : productList) {
            productMap.put(product.getId(), product);
        }
        for (LoanDraft loanDraft : loanDraftPage) {
            Product product = productMap.get(loanDraft.getProductId());
            if (product != null) {
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
