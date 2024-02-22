package AST.Statement;

import AST.Node.Exec;
import GameLogics.Engine.PlayerInstance;

import java.util.List;

public class BlockExc extends Exec {
    private final List<Exec> nodes;

    public BlockExc(List<Exec> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        for(Exec node : nodes){
            if(!node.execute(command)) return false;
        }
        return true;
    }
}
