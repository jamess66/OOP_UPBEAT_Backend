package AST.Statement;

import Models.Commands;

public class WhileExc extends IfExc {
    private int executionCount = 0;

    public WhileExc(Expr expression, Exec statements) {
        super(expression, statements, null);
        if (trueNode == null)
            trueNode = this;
    }

    private Exec getLastExecs(Exec exec) {
        while (exec != this && exec != null) {
            if (exec.next == this || exec.next == null) return exec;
            exec = exec.next;
        }
        return this;
    }

    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform WhileExc");
        if (super.condition.eval(command) > 0) {
            if (executionCount >= 10000)
                return next.execute(command);
            Exec last = getLastExecs(trueNode);
            if (last != this) last.next = this;
            executionCount++;
            return trueNode.execute(command);
        }
        return next.execute(command);
    }
}