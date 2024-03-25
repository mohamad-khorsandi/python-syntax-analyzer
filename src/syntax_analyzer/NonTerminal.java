package syntax_analyzer;


import java.util.ArrayList;

import static syntax_analyzer.Main.trace;

public class NonTerminal extends Variable {
    static String pattern = "<.*?>";
    public String name;
    public ArrayList<Clause> rule = new ArrayList<>();

    public NonTerminal(String name) {
        this.name = name;
    }

    public void setRule(String str) {
        var clauseStr = str.split("\\|");
        for (var s : clauseStr){
            rule.add(new Clause(s));
        }
    }

    @Override
    public Result consume(String text, int index) throws Exception {
        trace.add(this);
        for (Clause clause : this.rule) {
            Result res = clause.consume(text, index);

            if (res.suc)
                return res;
        }

        trace.pop();
        return Result.res(false, 0);
    }

    @Override
    public String toString() {
        return "<" + this.name + ">";
    }
}
