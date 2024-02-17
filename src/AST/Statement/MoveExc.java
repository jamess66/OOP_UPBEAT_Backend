package AST.Statement;

import Models.Commands;
import Models.Utility.Direction;

import static AST.Node.*;

public class MoveExc extends Exec {
    private final Direction direction;

    public MoveExc(Direction direction) {
        this.direction = direction;
    }

    @Override
public boolean execute(Commands command) {
        System.out.println("Perform MoveExc " + direction);
        return command.move(direction);
    }
}