import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;


public class ExprTokenizer2 {
    public String src, next;
    private int pos;
    private static final Pattern pattern = Pattern.compile(
            "\\b(?:if|then|else|while|end|collect|invest|move|shoot|random|budget|deposit|opponent|nearby)\\b" + // Keywords
                    "|[-+*/]" + // Arithmetic operators
                    "|[-+]?\\d*\\.\\d+|\\d+" + // Numbers
                    "|[\\(\\)\\{\\}\\[\\];,]" + // Punctuation
                    "|=" + // Assignment operator
                    "|\\b(?:upleft|downleft|downright|upright|up|down|left|right|done)\\b" + // Directions
                    "|\\b(?:[a-zA-Z][a-zA-Z0-9]*|deposit|budget|opponentLoc|cost|dir)\\b" + // Variables
                    "|\\b(?:nearby)\\b" + // Nearby keyword
                    "|\\/\\/.*\\n" + // C++-style comments
                    "|\\/\\/.*$" // C++-style comments
    );
    public ExprTokenizer2(String src) throws ExprErrorException {
        this.src = src;
        pos = 0;
        computeNext();
    }
    public boolean hasNextToken() { return next != null; }
    public void checkNextToken() throws ExprErrorException {
        if (!hasNextToken())
            throw new ExprErrorException("no more tokens");
    }
    public String peek() throws ExprErrorException {
        checkNextToken();
        return next;
    }
    public String consume() throws ExprErrorException {
        checkNextToken();
        String result = next;
        computeNext();
        return result;
    }
    private void computeNext() throws ExprErrorException { // ComputeNext function was rewritten by Copilot
        StringBuilder s = new StringBuilder();
        boolean hasDecimal = false;
        while (pos < src.length() && isWhitespace(src.charAt(pos)))
            pos++;  // ignore whitespace
        if (pos == src.length()) { next = null;  return; }  // no more tokens
        char c = src.charAt(pos);
        Matcher matcher = pattern.matcher(String. valueOf(c));
        if (matcher.find()){
            pos++;
            s.append(matcher.group().trim());
        }
//        if (isDigit(c) || c == '.') {  // start of number
//            s.append(c);
//            if (c == '.') hasDecimal = true;
//            for (pos++; pos < src.length(); pos++) {
//                c = src.charAt(pos);
//                if (isDigit(c)) {
//                    s.append(c);
//                } else if (c == '.' && !hasDecimal) {
//                    s.append(c);
//                    hasDecimal = true;
//                } else {
//                    break;
//                }
//            }
//        }
//        else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '('|| c == ')' ) {
//            s.append(c);  pos++;
//        } else throw new SyntaxErrorException("unknown character: " + c);
        next = s.toString();
    }

    public boolean peek(String s) throws ExprErrorException {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }
    public void consume(String s)
            throws ExprErrorException {
        if (peek(s))
            consume();
        else
            throw new ExprErrorException(" expected");
    }
}