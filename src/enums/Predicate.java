package enums;

import syntax_analyzer.NonTerminal;

import java.util.TreeMap;

public enum Predicate {
    LEVEL_IS_ZERO("parent.level==0"),
    LEVEL_IS_NOT_ZERO("parent.level>0");

    final String str;
    Predicate(String str) {
        this.str = str;
    }

    static public Predicate getPredicate(String s) {
        if (s.equals(LEVEL_IS_ZERO.str)) {
            return LEVEL_IS_ZERO;
        } else if (s.equals(LEVEL_IS_NOT_ZERO.str)) {
            return LEVEL_IS_NOT_ZERO;
        } else {
            throw new RuntimeException("Predicate is not valid: " + s);
        }
    }

    public static boolean execute(Predicate p, NonTerminal nonTerminal) {
        if (p == null) {
            return true;
        } else if (p.equals(LEVEL_IS_ZERO)) {
            return nonTerminal.level == 0;
        } else if (p.equals(LEVEL_IS_NOT_ZERO)) {
            return nonTerminal.level > 0;
        } else {
            throw new RuntimeException();
        }
    }

}
