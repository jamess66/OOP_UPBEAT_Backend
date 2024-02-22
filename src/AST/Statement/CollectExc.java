package AST.Statement;

import GameLogics.Engine.PlayerInstance;
import AST.Node.*;

public class CollectExc extends Exec {
    private final Expr expr;

    public CollectExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        return command.collect(expr.eval(command));
    }
}