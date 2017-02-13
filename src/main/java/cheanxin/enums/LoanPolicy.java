package cheanxin.enums;

/**
 * Created by 273cn on 17/1/3.
 */
public enum LoanPolicy {
    AFTER_TRANSFER(0, "过户后放款"),
    AFTER_MORTGAGE(1, "抵押后放款");

    private final int value;
    private final String desc;

    private LoanPolicy(int value, String desc) {
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
        LoanPolicy loanPolicies[] = LoanPolicy.values();
        int values[] = new int[loanPolicies.length];
        for (int i = 0; i < loanPolicies.length; i++) {
            values[i] = loanPolicies[i].value();
        }
        return values;
    }

    public static boolean contains(int value) {
        LoanPolicy loanPolicies[] = LoanPolicy.values();
        for (LoanPolicy loanPolicy : loanPolicies) {
            if (loanPolicy.value() == value)
                return true;
        }
        return false;
    }
}
