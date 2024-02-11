package src.AST.Statement;

import Models.Command;
import AST.Node.*;

public class InvestExc extends Exec {
    private final Expr expr;

    public InvestExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform InvestExc " + expr.toString());
        return gamecmd.collect(expr.eval(gamecmd));
    }
}