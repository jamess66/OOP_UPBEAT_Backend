package src.AST.Expression;

import AST.Node.Expr;
import Models.Command;

public class NumberExp extends Expr {
    private final long value;

    public NumberExp(long value) {
        this.value = value;
    }

    @Override
    public long eval(Command game) {
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
