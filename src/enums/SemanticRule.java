package enums;

import syntax_analyzer.NonTerminal;

public enum SemanticRule {
    INHERIT_LEVEL("child.level=parent.level"),
    INC_LEVEL("child.level=parent.level+1"),
    DEC_LEVEL("child.level=parent.level-1");
     
    final String str;
    SemanticRule(String str) {
        this.str = str;
    }

    static public SemanticRule getSemantic(String s) {
        if (s.equals(INHERIT_LEVEL.str)) {
            return INHERIT_LEVEL;
        } else if (s.equals(INC_LEVEL.str)) {
            return INC_LEVEL;
        } else if (s.equals(DEC_LEVEL.str)) {
            return DEC_LEVEL;
        } else {
            throw new RuntimeException("Semantic is not valid: " + s);
        }
    }

    static public void execute(SemanticRule semantic, NonTerminal parent, NonTerminal child) {
        if (semantic == null) {
            return;
        } else if (semantic.equals(INHERIT_LEVEL)) {
            child.level = parent.level;
        } else if (semantic.equals(INC_LEVEL)) {
            child.level = parent.level + 1;
        } else if (semantic.equals(DEC_LEVEL)) {
            child.level = parent.level - 1;
        } else {
            throw new RuntimeException();
        }
    }
}
