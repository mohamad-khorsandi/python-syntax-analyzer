package syntax_analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static syntax_analyzer.Variable.extractName;

public class Grammar {
    public static HashMap<String, NonTerminal> nonTerminals = new HashMap<>();

    public static void readGrammar(String dir, boolean print) {
        try {
            Files.walk(Paths.get(dir)).filter(Files::isRegularFile).forEach((name) -> {
                Grammar.parseFile(name.toString());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(print) {
            System.out.println(show());
        }
    }
    private static void parseFile(String path) throws RuntimeException {
        Scanner s = null;
        try {
            s = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        s.useDelimiter("\\n(?= *<.*?> *?::=)");

        while (s.hasNext()) {
            NonTerminal nonTerminal;
            var ruleStr = s.next().split("::=");
            String name = extractName(ruleStr[0]);
            if (nonTerminals.containsKey(name)) {
                nonTerminal = nonTerminals.get(name);
            } else {
                nonTerminal = new NonTerminal(name);
                nonTerminals.put(nonTerminal.name, nonTerminal);
            }
            nonTerminal.setRule(ruleStr[1]);
        }
    }

    public static void checkNonTerminalsHaveRule() {
        for (var a : Grammar.nonTerminals.values()) {
            if (a.rule.isEmpty()) {
                throw new RuntimeException("there is no production rule for " + a);
            }
        }
    }


    public static String show() {
        StringBuilder s = new StringBuilder();
        for (NonTerminal nt : nonTerminals.values()) {
            boolean f = true;
            s.append(nt.toString()).append(" ::=");

            for (Clause orClause : nt.rule) {
                if (!f) {
                    s.append(" |");
                }
                f = false;
                s.append(orClause.toString());
            }
            s.append("\n");
        }
        return s.toString();
    }
}
