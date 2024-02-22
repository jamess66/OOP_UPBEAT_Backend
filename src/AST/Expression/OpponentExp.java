package AST.Expression;

import GameLogics.Engine.PlayerInstance;

import static AST.Node.*;

public class OpponentExp extends Expr {
    @Override
    public long eval(PlayerInstance command) {
        return command.opponent();
    }

    @Override
    public String toString() {
        return "opponent";
    }
}