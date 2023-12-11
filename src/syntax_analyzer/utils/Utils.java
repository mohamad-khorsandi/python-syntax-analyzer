package syntax_analyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;


public class Utils {

    static public String readFile(String fileName, boolean print) {
        String inputString = null;
        try {
            inputString = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = inputString.split("\n");
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            String trimmedLine = line.replaceAll("\\s+$", "");
            if (!trimmedLine.isEmpty()) {
                result.append(trimmedLine).append("\n");
            }
        }

        String resultString = result.toString().stripTrailing();
        resultString = resultString.replaceAll("(?<=\\S) +(?!\\n|$| )", " ");
        if (print) {
            System.out.println("preprocessing result: -----------");
            System.out.println(resultString);
            System.out.println("---------------------------------\n");
        }
        return resultString;
    }

    public static void report(String code, int index) {
        if (index < 0) {
            throw new RuntimeException("Index out of range!");
        } else if (index >= code.length()) {
            throw new RuntimeException("Index out of range!");
        }

        String[] lines = code.split("\n", -1);
        int counter = 0;
        int currentIndex = 0;
        for (String line : lines) {
            counter++;
            int lineLength = line.length() + 1;
            if (currentIndex + lineLength > index) {
                System.out.println("error in line: " + counter);
                System.out.println(line);
                System.out.println(" ".repeat(index - currentIndex) + "^");
                return;
            }
            currentIndex += lineLength;
        }

        throw new RuntimeException("Line not found!");
    }
}
