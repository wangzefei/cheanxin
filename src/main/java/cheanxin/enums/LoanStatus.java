package cheanxin.enums;

/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanStatus {
    FIRST_REVIEW_ACCEPTED(1, "初审通过/复审待定价"),
    SECOND_REVIEW_PENDING(2, "复审已定价/复审待审核"),
    SECOND_REVIEW_ACCEPTED(4, "复审通过/待签约"),
    SECOND_REVIEW_REJECTED(8, "复审拒绝/待补充材料"),
    CONTRACT_ACCEPTED(16, "已签约/待放款"),
    CONTRACT_REJECTED(32, "拒绝合同"),
    LOAN_ABORTED(64, "贷款放弃");

    private final Integer value;
    private final String desc;

    LoanStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static int[] getAllValues() {
        LoanStatus productStatuses[] = LoanStatus.values();
        int values[] = new int[productStatuses.length];
        for (int i = 0; i < productStatuses.length; i++) {
            values[i] = productStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return LoanStatus.valueOf(value) != null;
    }
    
    public static LoanStatus valueOf(int value) {
        LoanStatus productStatuses[] = LoanStatus.values();
        for (LoanStatus productStatus : productStatuses) {
            if (productStatus.value() == value)
                return productStatus;
        }
        return null;
    }
}
