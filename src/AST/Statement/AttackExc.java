package AST.Statement;

import GameLogics.Engine.PlayerInstance;
import GameLogics.Utility.Direction;

import static AST.Node.*;

public class AttackExc extends Exec {
    private final Expr expression;
    private final Direction direction;

    public AttackExc(Expr expression, Direction direction) {
        this.expression = expression;
        this.direction = direction;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        return command.attack(
                direction,
                expression.eval(command)
        );
    }
}