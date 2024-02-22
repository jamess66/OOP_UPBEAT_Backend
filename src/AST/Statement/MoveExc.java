package AST.Statement;

import GameLogics.Engine.PlayerInstance;
import GameLogics.Utility.Direction;

import static AST.Node.*;

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