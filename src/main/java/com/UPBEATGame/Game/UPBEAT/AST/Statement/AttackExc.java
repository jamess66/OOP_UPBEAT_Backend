package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.Direction;

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