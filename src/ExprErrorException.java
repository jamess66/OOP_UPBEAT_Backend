class SyntaxErrorException extends Exception {
    public SyntaxErrorException(String message) {
        super(message);
    }
}

class LexicalErrorException extends Exception{
    public LexicalErrorException(String message){
        super(message);
    }
}