package src.AST.Expression;

import Models.Direction;
import Models.Command;

import static AST.Node.*;

public class NearbyExp extends Expr {
    private final Direction dir;

    public NearbyExp(Direction dir) {
        this.dir = dir;
    }

    @Override
    public long eval(Command gamecmd) {
        System.out.println("Perform nearby " + dir);
        return gamecmd.nearby(dir);
    }

    @Override
    public String toString() {
        return "nearby " + dir;
    }
}