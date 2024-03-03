package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
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