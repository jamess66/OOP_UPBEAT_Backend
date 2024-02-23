package com.UPBEATGame.Game.UPBEAT.ConstructionParser;

public abstract class ParserException extends RuntimeException {
    protected ParserException(String m) {
        super(String.format("%s", m));
    }

    public static class NoSuchCommandException extends ParserException {
        public NoSuchCommandException(String cmd) {
            super(String.format("Invalid command: -> '%s'.", cmd));
        }
    }

    public static class InvalidActionCommandException extends ParserException {
        public InvalidActionCommandException(String cmd) {
            super(String.format("Invalid Action: -> '%s'.", cmd));
        }
    }

    public static class InvalidDirectionException extends ParserException {
        public InvalidDirectionException(String dir) {
            super(String.format("Invalid direction: ->'%s'.", dir));
        }
    }

    public static class InvalidInfoExpressionException extends ParserException {
        public InvalidInfoExpressionException(String exr) {
            super(String.format("Invalid info-expression -> '%s'.", exr));
        }
    }

    public static class SpecVarIdentifierException extends ParserException {
        public SpecVarIdentifierException(String iden) {
            super(String.format("Identifier -> '%s' is not available.", iden));
        }
    }
    
    public static class NoMoreStatementException extends ParserException {
        public NoMoreStatementException() {
            super("No statement");
        }
    }
}