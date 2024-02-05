import java.util.NoSuchElementException;
import java.util.StringTokenizer;
public class ExprTokenizer extends StringTokenizer {
    private final String str;
    private String next;
    private int pos;

    public ExprTokenizer(String str) throws Exception {
        super(str);
        this.str = str;
        pos = 0;
        computeNext();
    }

    public boolean hasNextToken() {
        return next != null;
    }

    public void checkNextToken() {
        if (!hasNextToken()) throw new NoSuchElementException("No more tokens");
    }

    public String peek() {
        checkNextToken();
        return next;
    }

    public boolean peek(String s) {
        if (!hasNextToken()) return false;
        return next.equals(s);
    }

    public String consume() throws Exception {
        checkNextToken();
        String result = next;
        computeNext();
        return result;
    }

    public void consume(String s) throws Exception {
        if (peek(s)) {
            consume();
        } else throw new SyntaxErrorException(s + " expected");
    }

    private void computeNext() throws Exception {
        next = null;
        StringBuilder s = new StringBuilder();
        while (pos < str.length()) {
            char c = str.charAt(pos);
            if (isWhiteSpace(c)) {
                pos++;
                continue;
            }
            if (isDigit(c)) {
                s.append(c);
                pos++;
                while (pos < str.length() && isDigit(str.charAt(pos))) {
                    s.append(str.charAt(pos));
                    pos++;
                }
            }else  if (isCharacter(c)) {
                s.append(c);
                pos++;
                while (pos < str.length() && isCharacter(str.charAt(pos))) {
                    s.append(str.charAt(pos));
                    pos++;
                }
            } else if (isOperator(c)) {
                s.append(c);
                pos++;
            } else if (c == '(' || c == ')') {
                s.append(c);
                pos++;
            } else throw new LexicalErrorException("Unknown character: " + c);
            next = s.toString();
            break;
        }
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private boolean isWhiteSpace(char c) {
        return Character.isWhitespace(c);
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
    }

    private boolean isCharacter(char c) {
        return Character.isLetter(c);
    }
}
