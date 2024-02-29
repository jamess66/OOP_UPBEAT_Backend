package com.UPBEATGame.Game.UPBEAT.AST.Expression;

import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.PlayerInstance;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

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