package Tokenizer;

public class TokenizerException extends RuntimeException {
    public TokenizerException(String str) {
        super(str);
    }

    public static class NoMoreToken extends TokenizerException {
        private static String msg(String str) {
            if (str != null)
                return String.format(", prev -> " + str);
            else
                return "";
        }

        public NoMoreToken(String str) {
            super("need more token!!! -> " + msg(str));
        }
    }

    public static class ExceptChar extends TokenizerException {
        public ExceptChar(char c) {
            super("Except Char -> " + c);
        }
    }
}