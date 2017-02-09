package cheanxin.enums;

/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanStatus {
    FIRST_REVIEW_PENDING(0, "初审待审核"),
    FIRST_REVIEW_ACCEPTED(0, "初审通过"),
    FIRST_REVIEW_REJECTED(0, "初审退回");

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
