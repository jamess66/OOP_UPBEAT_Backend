public class ExprErrorException extends Exception{
    public ExprErrorException(String message){
        super(message);
    }
}

class EvalError extends Exception{
    public EvalError(String message){
        super(message);
    }
}