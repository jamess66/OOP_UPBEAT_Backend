package src.AST.Statement;

import Models.Command;
import Models.Direction;

import static AST.Node.*;

public class MoveExc extends Exec {
    private final Direction direction;

    public MoveExc(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform MoveExc " + direction);
        return gamecmd.move(direction);
    }
}