package src.AST;

public abstract class ASTErrorException extends RuntimeException {
    protected ASTErrorException(String m) {
        super(m);
    }

    public static class ExceptTokenErrorException extends ASTErrorException {
        public ExceptTokenErrorException(String token) {
            super("Excepted token: >> " + token);
        }
    }

    public static class UnknownOperator extends ASTErrorException {
        public UnknownOperator(String op) {
            super(String.format("Invalid Operator: %s.", op));
        }
    }

    public static class UndefinedIdentifier extends ASTErrorException {
        public UndefinedIdentifier(String iden) {
            super(String.format("Undefined identifier: '%s'.", iden));
        }
    }

    public static class IntegerRequired extends ASTErrorException {
        public IntegerRequired(String iden) {
            super(String.format("Integer required: '%s'.", iden));
        }
    }
}