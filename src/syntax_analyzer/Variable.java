package syntax_analyzer;

abstract public class Variable {
    public abstract Result consume(String s, int index) throws Exception;

    @Override
    public abstract String toString();

    public static String extractName(String s) {
        s = s.trim();
        return s.substring(1, s.length()-1);
    }
}

