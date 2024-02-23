package com.UPBEATGame.Game.UPBEAT.AST.Expression;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.Direction;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class NearbyExp extends Expr {
    private final Direction dir;

    public NearbyExp(Direction dir) {
        this.dir = dir;
    }

    @Override
    public long eval(PlayerInstance command) {
        return command.nearby(dir);
    }

    @Override
    public String toString() {
        return "nearby " + dir;
    }
}