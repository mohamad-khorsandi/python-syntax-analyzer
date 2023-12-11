package grammer;

import enums.Type;

public class RuleItem {
    public RuleItem(String str, Type type) {
        this.str = str;
        this.type = type;
    }

    public String str;
    public Type type;

    @Override
    public String toString() {
        return str;
    }
}