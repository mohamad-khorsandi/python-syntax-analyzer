package grammer;
import enums.Type;
import syntax_analyzer.utils.Utils;

import java.util.*;

import static enums.Type.*;

public class Grammar {
    public ArrayList<Rule> rules = new ArrayList<>();

    public void parseFile(String path) throws RuntimeException {
        String file = Utils.readFile(path, false);
        String pattern = "^<.+>::=";
        Rule curRule = null;

        for (String token : List.of(file.split("\\s+"))) {
            if (token.matches(pattern)) {
                curRule = new Rule(token.substring(0, token.length()-3));
                this.rules.add(curRule);

            } else if (curRule == null) {
                throw new RuntimeException();

            } else if(token.startsWith("Predicate:")) {
                curRule.setPredicate(token.split(":")[1]);

            } else if(token.startsWith("SemanticRule:")) {
                curRule.setSemanticRule(token.split(":")[1]);

            } else {
                Type st = findType(token).orElseThrow(() -> new RuntimeException(token + " is invalid in grammar"));
                curRule.addItem(token, st);
            }
        }
    }
}
