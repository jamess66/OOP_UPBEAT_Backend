package AST.Statement;

import GameLogics.Engine.PlayerInstance;
import AST.Node.*;

public class InvestExc extends Exec {
    private final Expr expr;

    public InvestExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        return command.collect(expr.eval(command));
    }
}