package syntax_analyzer;

import syntax_analyzer.utils.GrammarTracker;
import syntax_analyzer.utils.Utils;

import static syntax_analyzer.Grammar.nonTerminals;

public class Main {
    static GrammarTracker trace = new GrammarTracker();
    static String startingSymbol = "java_program";

    public static void main(String[] args) throws Exception {
        Grammar.readGrammar("grammar_src", true);
        Grammar.checkNonTerminalsHaveRule();

        if (!nonTerminals.containsKey(startingSymbol)) {
            throw new RuntimeException("startingSymbol not found: " + startingSymbol);
        }
        String text = Utils.readFile("codes/code", true);
        Result res = nonTerminals.get(startingSymbol).consume(text, 0);

        if (res.suc && text.length() == res.consumed) {
            System.out.println("code can be compile successfully!");
        } else if (text.length() != res.consumed){
            System.out.println("code could not be consumed entirely");
            Utils.report(text, trace.mostConsumed);
            trace.report();
        }else {
            Utils.report(text, trace.mostConsumed);
            trace.report();
        }
    }
}