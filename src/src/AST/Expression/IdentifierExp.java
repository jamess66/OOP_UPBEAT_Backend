package src.AST.Expression;

import AST.Node.*;
import AST.ASTErrorException;
import Models.Command;

public class IdentifierExp extends Expr {
    private final String idf;
    public IdentifierExp(String idf) {
        this.idf = idf;
    }
    @Override
    public long eval(Command game) {

        System.out.println("Perform IdentifierExp " + idf);
        if(game.getIdentifiers().containsKey(idf)){
            return game.getIdentifiers().get(idf);
        }else{
            throw new ASTErrorException.UndefinedIdentifier(idf);
        }
    }


    @Override
    public String toString(){
        return idf;
    }
}