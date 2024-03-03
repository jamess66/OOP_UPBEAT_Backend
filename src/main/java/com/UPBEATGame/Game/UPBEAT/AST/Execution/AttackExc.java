package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility.Direction;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class AttackExc extends Exec {
    private final Expr expression;
    private final Direction direction;

    public AttackExc(Expr expression, Direction direction) {
        this.expression = expression;
        this.direction = direction;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        return command.attack(direction, expression.eval(command));
    }
}