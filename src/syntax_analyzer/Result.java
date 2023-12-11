package syntax_analyzer;

import java.util.ArrayList;
import java.util.Objects;

public class Result {
    public Result(boolean suc, int consumed) {
        this.suc = suc;
        this.consumed = consumed;
    }
    static Result res(boolean suc, int consumed) {
        return new Result(suc, consumed);
    }

    @Override
    public String toString() {
        return this.suc +  " " + this.consumed;
    }

    boolean suc;
    int consumed;
}
