package ConstructionParser.Tokenizer;

public class TokenException extends RuntimeException {
    public TokenException(String str) {
        super(str);
    }

    public static class NoMoreToken extends TokenException {
        private static String msg(String str) {
            if (str != null)
                return String.format(", prev -> " + str);
            else
                return "";
        }

        public NoMoreToken(String str) {
            super("No more -> " + msg(str));
        }
    }

    public static class ExceptChar extends TokenException {
        public ExceptChar(char c) {
            super("Except char -> " + c);
        }
    }
}