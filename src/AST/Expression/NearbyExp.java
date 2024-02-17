package AST.Expression;

import Models.Utility.Direction;
import Models.Commands;

import static AST.Node.*;

public class NearbyExp extends Expr {
    private final Direction dir;

    public NearbyExp(Direction dir) {
        this.dir = dir;
    }

    @Override
    public long eval(Commands command) {
        System.out.println("Perform nearby " + dir);
        return command.nearby(dir);
    }

    @Override
    public String toString() {
        return "nearby " + dir;
    }
}