package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.AST.Node.*;

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