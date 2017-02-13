package cheanxin.enums;

/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanStatus {
    FIRST_REVIEW_PENDING(0, "初审待审核"),
    FIRST_REVIEW_ACCEPTED(1, "初审通过/复审待定价"),
    FIRST_REVIEW_REJECTED(2, "初审拒绝"),
    FIRST_REVIEW_RETURNED(3, "初审退回修改"),
    SECOND_REVIEW_PENDING(4, "复审已定价/复审待审核"),
    SECOND_REVIEW_ACCEPTED(5, "复审通过/待签约"),
    SECOND_REVIEW_REJECTED(6, "复审拒绝"),
    SECOND_REVIEW_ABORTED(7, "复审拒绝后放弃"),
    CONTRACT_ACCEPTED(8, "已签约/待放款"),
    CONTRACT_REJECTED(9, "拒绝合同"),
    CONTRACT_ABORTED(10, "拒绝合同后放弃");

    private final int value;
    private final String desc;

    LoanStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int value() {
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
