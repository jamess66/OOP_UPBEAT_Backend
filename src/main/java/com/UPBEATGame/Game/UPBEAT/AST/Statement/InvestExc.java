package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class InvestExc extends Exec {
    private final Expr expr;

    public InvestExc(Expr expr) {
        this.expr = expr;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        System.out.println("invest");
        return command.invest(expr.eval(command));
    }
}