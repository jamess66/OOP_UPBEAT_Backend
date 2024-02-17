package AST.Statement;

import AST.Node.*;
import Models.Commands;

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
    public boolean execute(Commands command) {
        System.out.println("Perform IfExc " + condition + " " + trueNode + " " + falseNode);
        trueNode.next = next;
        falseNode.next = next;
        if(condition.eval(command) > 0){
            return trueNode.execute(command);
        } else {
            return falseNode.execute(command);
        }
    }
}