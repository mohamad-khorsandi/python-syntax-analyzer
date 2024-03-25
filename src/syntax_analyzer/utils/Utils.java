package syntax_analyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Utils {

    static public String readFile(String fileName, boolean print) throws IOException {

        String text = new String(Files.readAllBytes(Paths.get(fileName)));
        //remove comments
//        String resultString = text.replaceAll( "//.*$", "" );
        String resultString = text.replaceAll( "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1 " );
        resultString = resultString.replaceAll("\\s+", " ");
//        resultString = resultString + "EOF";

        if (print) {
            printTokens(resultString);
        }
        return resultString;
    }

    static private void printTokens(String tokens) {
        System.out.println("preprocessing result: -----------");
        System.out.println(tokens);
        System.out.println("---------------------------------\n");
    }

    public static void report(String code, int index) {
        if (index < 0) {
            throw new RuntimeException("Index out of range!");
        } else if (index > code.length()) {
            throw new RuntimeException("Index out of range!");
        }
        int a = Integer.max(0, index-10);
        int b = Integer.min(code.length(), index+10);
        System.out.println(code.substring(a, b));
        System.out.println(" ".repeat(index) + "^");
    }
}
