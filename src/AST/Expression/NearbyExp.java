package AST.Expression;

import GameLogics.Utility.Direction;
import GameLogics.Engine.PlayerInstance;

import static AST.Node.*;

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