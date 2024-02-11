package src.AST.Statement;

import AST.Node.*;
import Models.Command;

public class IfExc extends Exec {
    protected final Expr condition;
    protected Exec trueNode;
    protected Exec falseNode;

    public IfExc(Expr condition, Exec trueNode, Exec falseNode) {
        this.condition = condition;
        this.trueNode = trueNode;
        this.falseNode = falseNode;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform IfExc " + condition + " " + trueNode + " " + falseNode);
        trueNode.next = next;
        falseNode.next = next;
        if(condition.eval(gamecmd) > 0){
            return trueNode.execute(gamecmd);
        } else {
            return falseNode.execute(gamecmd);
        }
    }
}