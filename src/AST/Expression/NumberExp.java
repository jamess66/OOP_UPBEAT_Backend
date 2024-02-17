package AST.Expression;

import AST.Node.Expr;
import Models.Commands;

public class NumberExp extends Expr {
    private final long value;

    public NumberExp(long value) {
        this.value = value;
    }

    @Override
    public long eval(Commands command) {
        System.out.println("Perform NumberExp " + value);
        return value;
    }

    public long eval() {
        System.out.println("Perform NumberExp " + value);
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
