package com.UPBEATGame.Game.UPBEAT.AST.Expression;

import com.UPBEATGame.Game.UPBEAT.AST.Node.Expr;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;

public class NumberExp extends Expr {
    private final long value;

    public NumberExp(long value) {
        this.value = value;
    }

    @Override
    public long eval(PlayerInstance command) {
        if(command == null) return eval();
        return value;
    }

    public long eval() {
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
