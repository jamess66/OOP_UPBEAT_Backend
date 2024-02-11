package src.AST.Expression;

import Models.Command;

import static AST.Node.*;

public class OpponentExp extends Expr {
    @Override
    public long eval(Command gamecmd) {
        System.out.println("Perform OpponentExp");
        return gamecmd.opponent();
    }

    @Override
    public String toString() {
        return "opponent";
    }
}