package syntax_analyzer;

import java.util.ArrayList;

import static syntax_analyzer.Result.res;

public class Clause extends ArrayList<Variable> {
    public Clause(String str) {
        for(var s : str.split("(?<=([\">])) +")) {
            s = s.replaceAll("\\s*", "");
            if (s.isEmpty())
                continue;


            if (s.matches(NonTerminal.pattern)) {
                String name = Variable.extractName(s);
                NonTerminal nonTerminal;
                if (Grammar.nonTerminals.containsKey(name)) {
                    nonTerminal = Grammar.nonTerminals.get(name);
                } else {
                    nonTerminal = new NonTerminal(name);
                    Grammar.nonTerminals.put(name, nonTerminal);
                }
                this.add(nonTerminal);
            } else if (s.matches(Terminal.pattern)) {
                String name = Variable.extractName(s);
                this.add(new Terminal(name));
            } else {
                throw new RuntimeException("not expected in grammar : " + str);
            }
        }
    }

    public Result consume(String text,int index) throws Exception {
        int consumed = 0;
        for (var var : this) {
            Result res = var.consume(text, index+consumed);

            if (!res.suc)
                return res(false, consumed+res.consumed);

            consumed += res.consumed;
        }

        return new Result(true, consumed);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Variable item : this) {
            s.append(" ").append(item.toString());
        }
        return s.toString();
    }
}
