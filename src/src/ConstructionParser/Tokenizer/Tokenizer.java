package src.ConstructionParser.Tokenizer;

public interface Tokenizer {
    boolean hasNext();
    String consume();
    boolean consume(String str);
    String peek();
    boolean peek(String str);
    boolean peekIdentifier();
    //boolean peekNumber();
}
