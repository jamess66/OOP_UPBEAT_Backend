package AST.Expression;

import AST.Node.*;
import AST.ASTErrorException;
import Config.ConfigLoader;
import GameLogics.Engine.PlayerInstance;

import java.util.Random;

public class IdentifierExp extends Expr {
    private final String idf;
    public IdentifierExp(String idf) {
        this.idf = idf;
    }
    @Override
    public long eval(PlayerInstance command) {
        switch (idf) {
            case "random" -> {
                Random random = new Random();
                return random.nextLong(0, 999);
            }
            case "rows" -> {
                return ConfigLoader.rows;
            }
            case "cols" -> {
                return ConfigLoader.cols;
            }
            case "currow" -> {
                return command.getCurrentRow();
            }
            case "curcol" -> {
                return command.getCurrentCol();
            }
            case "budget" -> {
                return command.getBudget();
            }
            case "deposit" -> {
                return (long) command.getCurrentRegion().getDeposit();
            }
            case "int" -> {
                return (long) (ConfigLoader.interest_pct);
            }
            case "maxdeposit" -> {
                return ConfigLoader.max_dep;
            }
        }
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