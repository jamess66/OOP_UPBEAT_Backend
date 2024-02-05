import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ExprTokenizer {
    //Chat GPT
    List<String> Line;
    private int pos;
    public ExprTokenizer(String line){
        pos = 0 ;
        this.Line = tokenize(line);
    }
    private static final Pattern pattern = Pattern.compile(
            "#[^\\n]*"+
                    "|\\b(?:if|then|else|while|end|collect|invest|move|shoot|random|budget|deposit|opponent|nearby)\\b" + // Keywords
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
    public static List<String> tokenize(String inputString) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            if (Pattern.matches("#[^\\n]*", matcher.group().trim())){return tokens;}
            else {
                tokens.add(matcher.group().trim());
            }
        }
        return tokens;
    }
    public boolean hasNextToken() {
        return Line.get(pos) != null; }
    public String peek() throws ExprErrorException {
        checkNextToken();
        return Line.get(pos);
    }
    public void checkNextToken() throws ExprErrorException {
        if (!hasNextToken()) throw new ExprErrorException("Have no token");
    }
    public String consume() throws ExprErrorException {
        checkNextToken();
        String result = Line.get(pos);
        if(pos+1 < Line.size()){
            pos++;
        }
        return result;
    }
    public boolean peek(String s) throws ExprErrorException {
        if (!hasNextToken()) return false;
        return peek().equals(s);
    }
    public void consume(String s) throws ExprErrorException {
        if (peek(s))
            consume();
        else
            throw new ExprErrorException("expected next word");
    }


}