package cheanxin.enums;

/**
 * Created by Jawinton on 17/02/09.
 */
public enum LoanDraftStatus {
    FIRST_DRAFT(1, "初稿"),
    SECOND_DRAFT(2, "复稿"),
    DRAFT_REJECTED(4, "复稿退回待修改"),
    DRAFT_ACCEPTED(8, "复稿通过"),
    DRAFT_ABORTED(16, "复稿直接拒绝");

    private final Integer value;
    private final String desc;

    LoanDraftStatus(Integer value, String desc) {
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
        LoanDraftStatus productStatuses[] = LoanDraftStatus.values();
        int values[] = new int[productStatuses.length];
        for (int i = 0; i < productStatuses.length; i++) {
            values[i] = productStatuses[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        return LoanDraftStatus.valueOf(value) != null;
    }
    
    public static LoanDraftStatus valueOf(int value) {
        LoanDraftStatus productStatuses[] = LoanDraftStatus.values();
        for (LoanDraftStatus productStatus : productStatuses) {
            if (productStatus.value() == value)
                return productStatus;
        }
        return null;
    }
}
