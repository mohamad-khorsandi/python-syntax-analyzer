package syntax_analyzer;

import enums.Predicate;
import enums.SemanticRule;
import grammer.Rule;
import grammer.RuleItem;
import enums.Type;

import java.util.ArrayList;
import java.util.Iterator;

import static syntax_analyzer.Main.trace;
import static syntax_analyzer.Result.res;

public class NonTerminal extends Consumer {
    protected NonTerminal(String name, NonTerminal parent) {
        this.name = name;
        if (parent == null) {
            this.level = 0;
        } else {
            SemanticRule.execute(parent.rule.semanticRule, parent, this);
        }
        this.rule = findRule();
    }

    private final String name;
    public int level;
    private final Rule rule;

    @Override
    public Result consume(String text, int index) throws Exception {
        trace.add(this);
        int maxConsumed = 0;
        for (ArrayList<RuleItem> OrClause : rule.second) {
            Result res = this.consumeItemSequence(OrClause, index, text);

            if (res.suc) {
                trace.pop();
                return res;
            }

            if (maxConsumed < res.consumed)
                maxConsumed = res.consumed;
        }

        trace.pop();
        return res(false, maxConsumed);
    }

    private Result consumeItemSequence(ArrayList<RuleItem> sequence, int index, String text) throws Exception {

        int consumed = 0;
        for (RuleItem item : sequence) {
            Result itemRes = res(false, 0);

            if (item.type.equals(Type.TERMINAL)) {
                itemRes = new Terminal(item.str).consume(text, index+consumed);
            } else if (item.type.equals(Type.NON_TERMINAL)) {
                itemRes = new NonTerminal(item.str, this).consume(text, index+consumed);
            }

            if (!itemRes.suc)
                return res(false, consumed+itemRes.consumed);

            consumed += itemRes.consumed;
        }

        return new Result(true, consumed);
    }

    private Rule findRule() {
        return Main.grammar.rules.stream()
                .filter(rule -> rule.first.equals(this.name))
                .filter(rule -> Predicate.execute(rule.predicate, this))
                .findFirst().orElseThrow(() -> new RuntimeException("there is no rule for this non terminal: " + name));
    }

    @Override
    public String toString() {
        return "<" + this.name + ">";
    }
}
