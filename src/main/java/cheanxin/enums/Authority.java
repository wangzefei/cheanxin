package cheanxin.enums;

/**
 * Created by Jawinton on 17/01/03.
 */
public enum Authority {
    // 贷款审核相关权限
    ROLE_FIRST_REVIEW_ACCEPTED_TO_FIRST_REVIEW_ACCEPTED,
    ROLE_FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING,
    ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED,
    ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED,
    ROLE_SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING,
    ROLE_SECOND_REVIEW_REJECTED_TO_LOAN_ABORTED,
    ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED,
    ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED,
    ROLE_CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING,
    ROLE_CONTRACT_REJECTED_TO_LOAN_ABORTED,

    // 贷款草稿相关权限
    ROLE_FIRST_DRAFT_TO_FIRST_DRAFT,
    ROLE_FIRST_DRAFT_TO_SECOND_DRAFT,
    ROLE_SECOND_DRAFT_TO_SECOND_DRAFT,
    ROLE_SECOND_DRAFT_TO_DRAFT_ABORTED,
    ROLE_SECOND_DRAFT_TO_DRAFT_REJECTED,
    ROLE_SECOND_DRAFT_TO_DRAFT_ACCEPTED,

    // 产品相关权限
    ROLE_PENDING_REVIEW_TO_ACCEPTED,
    ROLE_PENDING_REVIEW_TO_REJECTED
}
