package src.AST.Statement;

import Models.Command;
import AST.Node.*;

public class CollectExc extends Exec {
    private final Expr expr;

    public CollectExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform CollectExc " + expr);
        return gamecmd.collect(expr.eval(gamecmd));
    }
}