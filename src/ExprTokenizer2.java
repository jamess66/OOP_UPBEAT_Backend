import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;


public class ExprTokenizer2 implements  Tokenizer{
    public String src, next;
    private int pos;
    private static final Pattern pattern = Pattern.compile(
            "#[^\\n]*" + // Match comments
                    "|\\b(?:if|then|else|while|end|collect|invest|move|shoot|random|budget|deposit|opponent|nearby)\\b" + // Keywords
                    "|[-+*/]" + // Arithmetic operators
                    "|[-+]?\\d*\\.\\d+|\\d+" + // Numbers
                    "|[\\(\\)\\{\\}\\[\\];,]" + // Punctuation
                    "|=" + // Assignment operator
                    "|\\b(?:upleft|downleft|downright|upright|up|down|left|right|done)\\b" + // Directions
                    "|\\b(?:t|m|deposit|budget|opponentLoc|cost|dir)\\b" + // Variables
                    "|\\b(?:nearby)\\b" + // Nearby keyword
                    "|\\/\\/.*\\n" + // C++-style comments
                    "|\\/\\/.*$" // C++-style comments
    );
    public ExprTokenizer2(String src) throws SyntaxErrorException {
        this.src = src;
        pos = 0;
        computeNext();
    }
    public boolean hasNextToken() { return next != null; }
    public void checkNextToken() throws SyntaxErrorException {
        if (!hasNextToken())
            throw new SyntaxErrorException("no more tokens");
    }
    public String peek() throws SyntaxErrorException {
        checkNextToken();
        return next;
    }
    public String consume() throws SyntaxErrorException {
        checkNextToken();
        String result = next;
        computeNext();
        return result;
    }
    private void computeNext() throws SyntaxErrorException { // ComputeNext function was rewritten by Copilot
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

    public boolean peek(String s) throws SyntaxErrorException {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }
    public void consume(String s)
            throws SyntaxErrorException {
        if (peek(s))
            consume();
        else
            throw new SyntaxErrorException(" expected");
    }
}