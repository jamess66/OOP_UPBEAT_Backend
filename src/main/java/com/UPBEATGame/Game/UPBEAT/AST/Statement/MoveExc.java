package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.Direction;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class MoveExc extends Exec {
    private final Direction direction;

    public MoveExc(Direction direction) {
        this.direction = direction;
    }

    @Override
public boolean execute(PlayerInstance command) {
        return command.move(direction);
    }
}