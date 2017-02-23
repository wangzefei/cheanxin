package cheanxin.enums;

/**
 * Created by 273cn on 17/1/3.
 */
public enum LoanOperation {
    UPDATE(1, "update"),
    SUBMIT(2, "submit"),
    ACCEPT(3, "accept"),
    REJECT(4, "reject"),
    ABORT(5, "abort");

    private final Integer value;
    private final String methodName;

    LoanOperation(Integer value, String desc) {
        this.value = value;
        this.methodName = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public static boolean contains(int value) {
        return valueOf(value) != null;
    }

    public static LoanOperation valueOf(int value) {
        LoanOperation loanOperations[] = LoanOperation.values();
        for (LoanOperation loanOperation : loanOperations) {
            if (loanOperation.value() == value)
                return loanOperation;
        }
        return null;
    }
}
