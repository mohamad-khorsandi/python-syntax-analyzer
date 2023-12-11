package syntax_analyzer;

abstract public class Consumer {
    public abstract Result consume(String s, int index) throws Exception;

    @Override
    public abstract String toString();
}

