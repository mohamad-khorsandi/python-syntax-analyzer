package syntax_analyzer.utils;

import syntax_analyzer.Consumer;

import java.util.*;

import static java.lang.Math.min;

public class GrammarTracker extends Stack<Consumer> {
    public int mostConsumed = 0;
    public Stack<Consumer> bestStack;

    public void saveIfMax (int consumed) {
        if (mostConsumed < consumed) {
            bestStack = new Stack<>();
            bestStack.addAll(this);
            mostConsumed = consumed;
        }
    }

    public void report() {
        LinkedHashSet<String> set = new LinkedHashSet<>();

        String suggest = bestStack.pop().toString();

        while (!bestStack.empty() && set.size() <= 5) {
            set.add(bestStack.pop().toString());
        }

        System.out.print("expected: \n");
        Stack<String> reverser = new Stack<>();
        set.forEach(reverser::push);
        while (!reverser.empty()) {
            System.out.print(reverser.pop() + " ");
        }

        System.out.print("\n\nsuggested character: " + suggest);

    }
}
