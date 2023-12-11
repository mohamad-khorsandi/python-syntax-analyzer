package grammer;
import enums.Predicate;
import enums.SemanticRule;
import enums.Type;

import java.util.ArrayList;

public class Rule {
    public Rule(String first) {
        this.first = first.substring(1, first.length()-1);
        second.add(new ArrayList<>());
        this.predicate = null;
        this.semanticRule = null;
    }

    public String first;
    public ArrayList<ArrayList<RuleItem>> second = new ArrayList<>();
    public SemanticRule semanticRule;
    public Predicate predicate;

    public void addItem(String str, Type t) {
        if (t.equals(Type.OR)) {
            second.add(new ArrayList<>());
        } else {
            str = str.substring(1, str.length()-1);
            str = str.replace("\\s", " ");
            str = str.replace("\\n", "\n");
            second.get(second.size()-1).add(new RuleItem(str, t));
        }
    }

    public void setPredicate(String s) {
        this.predicate =Predicate.getPredicate(s);
    }

    public void setSemanticRule(String s) {
        this.semanticRule = SemanticRule.getSemantic(s);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(first);
        s.append(" -> ");
        for (var orClause : second) {
            for (var item : orClause) {
                s.append(" ").append(item.str).append("(").append(item.type.toString().charAt(0)).append(")");
            }
            s.append(" |");
        }
        return s.toString();
    }
}
