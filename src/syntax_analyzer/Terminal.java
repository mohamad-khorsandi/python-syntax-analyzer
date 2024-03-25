package syntax_analyzer;

import static syntax_analyzer.Main.trace;
import static syntax_analyzer.Result.res;

public class Terminal extends Variable {
    protected Terminal(String name) {
        this.value = name;
    }

    static String pattern = "\".*?\"";
    String value;

    @Override
    public Result consume(String s, int index) {
        if (s.startsWith(value, index)) {
            return res(true, value.length());
        } else {
            trace.push(this);
            trace.saveIfMax(index);
            trace.pop();
            return res(false, 0);
        }
    }

    @Override
    public String toString() {
        return "\"" + this.value + "\"";
    }
}
