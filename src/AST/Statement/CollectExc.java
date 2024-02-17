package AST.Statement;

import Models.Commands;
import AST.Node.*;

public class CollectExc extends Exec {
    private final Expr expr;

    public CollectExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform CollectExc " + expr);
        return command.collect(expr.eval(command));
    }
}