package AST.Expression;

import Models.Commands;

import static AST.Node.*;

public class OpponentExp extends Expr {
    @Override
    public long eval(Commands command) {
        System.out.println("Perform OpponentExp");
        return command.opponent();
    }

    @Override
    public String toString() {
        return "opponent";
    }
}