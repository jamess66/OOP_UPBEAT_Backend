package AST.Expression;

import AST.Node.*;
import AST.ASTErrorException;
import Models.Commands;

public class IdentifierExp extends Expr {
    private final String idf;
    public IdentifierExp(String idf) {
        this.idf = idf;
    }
    @Override
    public long eval(Commands command) {

        System.out.println("Perform IdentifierExp " + idf);
        if(command.getIdentifiers().containsKey(idf)){
            return command.getIdentifiers().get(idf);
        }else{
            throw new ASTErrorException.UndefinedIdentifier(idf);
        }
    }


    @Override
    public String toString(){
        return idf;
    }
}