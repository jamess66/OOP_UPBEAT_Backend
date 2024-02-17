package AST.Statement;

import AST.Node.Exec;
import Models.Commands;

import java.util.List;

public class BlockExc extends Exec {
    private final List<Exec> nodes;

    public BlockExc(List<Exec> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform BlockExc " + nodes.toString());
        for(Exec node : nodes){
            if(!node.execute(command)) return false;
        }
        return true;
    }
}
