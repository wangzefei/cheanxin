package cheanxin.service.impl;

import cheanxin.data.ProductRepository;
import cheanxin.domain.Product;
import cheanxin.domain.ProductLog;
import cheanxin.domain.User;
import cheanxin.enums.ProductStatusTransfer;
import cheanxin.service.ProductLogService;
import cheanxin.service.ProductService;
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
 * Created by 273cn on 17/01/07.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLogService productLogService;

    @Override
    public Product save(Product unsavedProduct) {
        return productRepository.save(unsavedProduct);
    }

    @Override
    public void remove(long id) {
        productRepository.delete(id);
    }

    @Override
    public Product getOne(long id) {
        return productRepository.findOne(id);
    }

    private class SearchProduct {
        private String username;
        private long productTemplateId;
        private String name;
        private int status;

        public SearchProduct() {}


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public long getProductTemplateId() {
            return productTemplateId;
        }

        public void setProductTemplateId(long productTemplateId) {
            this.productTemplateId = productTemplateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
     * @param searchProduct
     * @return
     */
    private Specification<Product> getWhereClause(final SearchProduct searchProduct){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (searchProduct.getProductTemplateId() >= 0L) {
                    predicate.add(cb.equal(root.get("productTemplateId").as(Long.class), searchProduct.getProductTemplateId()));
                } else if (searchProduct.getProductTemplateId() == -1L) {
                    predicate.add(cb.gt(root.get("productTemplateId").as(Long.class), 0L));
                }
                if (searchProduct.getUsername() != null && !searchProduct.getUsername().trim().isEmpty()) {
                    predicate.add(cb.equal(root.get("creatorUsername").as(String.class), searchProduct.getUsername()));
                }
                if (searchProduct.getName() != null && !searchProduct.getName().trim().isEmpty()) {
                    predicate.add(cb.like(root.get("name").as(String.class), "%" + searchProduct.getName() + "%"));
                }
                if (searchProduct.getStatus() >= 0) {
                    predicate.add(cb.equal(root.get("status").as(Integer.class), searchProduct.getStatus()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    public Page<Product> list(String username, long productTemplateId, String name, int status, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        SearchProduct searchProduct = new SearchProduct();
        searchProduct.setName(name);
        searchProduct.setProductTemplateId(productTemplateId);
        searchProduct.setUsername(username);
        searchProduct.setStatus(status);
        Specification<Product> specification = this.getWhereClause(searchProduct);
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Product> list(long productTemplateId, String name, int status, int page, int size) {
        return list(null, productTemplateId, name, status, page, size);
    }

    @Override
    @Transactional
    public Product review(User user, Product fromProduct, Product toProduct) {
        // save from status before save product.
        int fromStatus = fromProduct.getStatus().intValue();
        int toStatus = toProduct.getStatus().intValue();
        fromProduct.setStatus(toStatus);
        Product savedProduct = save(fromProduct);

        // save product operation log.
        ProductLog productLog = new ProductLog(
                savedProduct.getId(),
                user.getUsername(),
                ProductStatusTransfer.valueOf(fromStatus, toStatus).getValue(),
                toProduct.getRemark(),
                System.currentTimeMillis() / 1000);
        productLogService.save(productLog);

        return savedProduct;
    }

    @Override
    public boolean hasChildProducts(Product product) {
        if (product.getProductTemplateId() != 0L)
            return false;
        return productRepository.findByProductTemplateId(product.getId(), new PageRequest(0, 1)).hasContent();
    }
}
