package syntax_analyzer;

import grammer.Grammar;
import syntax_analyzer.utils.GrammarTracker;
import syntax_analyzer.utils.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {
    static Grammar grammar = new Grammar();
    static GrammarTracker trace = new GrammarTracker();

    public static void main(String[] args) throws Exception {
        Files.walk(Paths.get("grammar_src")).filter(Files::isRegularFile).forEach((name) -> grammar.parseFile(name.toString()));

        String startingSymbol = "file";
        String textFile = Utils.readFile("pyCode/python_code2", true);
        textFile = textFile + " EOF";
        boolean res = evaluate(startingSymbol, textFile);
        if (! res) {
            trace.report();
        } else {
            System.out.println("code can be compile successfully!");
        }
    }

    static boolean evaluate(String startingSymbol, String text) throws Exception {

        NonTerminal nonTerminal = new NonTerminal(startingSymbol, null);

        Result result = nonTerminal.consume(text, 0);

        if (result.consumed == text.length() && result.suc) {
            return true;

        } else if (result.consumed == text.length()) {
            System.out.println("input text is too short");
            return false;

        } else {
            Utils.report(text, trace.mostConsumed);
            return false;
        }
    }
}