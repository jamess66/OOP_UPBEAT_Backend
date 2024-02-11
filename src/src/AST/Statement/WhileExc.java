package src.AST.Statement;

import Models.Command;

public class WhileExc extends IfExc {
    private int executionCount = 0;

    public WhileExc(Expr expression, Exec statements) {
        super(expression, statements, null);
        if (trueNode == null)
            trueNode = this;
    }

    private Exec getLastNode(Exec node) {
        while (node != this && node != null) {
            if (node.next == this || node.next == null) return node;
            node = node.next;
        }
        return this;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform WhileExc");
        if (super.condition.eval(gamecmd) > 0) {
            if (executionCount >= 10000)
                return next.execute(gamecmd);
            Exec last = getLastNode(trueNode);
            if (last != this)
                last.next = this;
            executionCount++;
            return trueNode.execute(gamecmd);
        }
        return next.execute(gamecmd);
    }
}