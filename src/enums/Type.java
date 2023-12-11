package enums;

import java.util.Optional;

public enum Type {
    OR("\\|"),
    TERMINAL("\".*\""),
    NON_TERMINAL("<[a-z 0-9_*?+]+>");

    Type(String regex) {
        this.regex = regex;
    }
    boolean isValid(String s) {
        return s.matches(this.regex);
    }
    String regex;

    public static Optional<Type> findType(String token) {
        if (OR.isValid(token)) {
            return Optional.of(OR);
        } else if (NON_TERMINAL.isValid(token)) {
            return Optional.of(NON_TERMINAL);
        } else if(TERMINAL.isValid(token)) {
            return Optional.of(TERMINAL);
        }
        return Optional.empty();
    }
}
