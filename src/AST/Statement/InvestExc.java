package AST.Statement;

import Models.Commands;
import AST.Node.*;

public class InvestExc extends Exec {
    private final Expr expr;

    public InvestExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform InvestExc " + expr.toString());
        return command.collect(expr.eval(command));
    }
}