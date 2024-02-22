package AST.Expression;

import AST.Node.Expr;
import GameLogics.Engine.PlayerInstance;

public class NumberExp extends Expr {
    private final long value;

    public NumberExp(long value) {
        this.value = value;
    }

    @Override
    public long eval(PlayerInstance command) {
        if(command == null) return eval();
        return value;
    }

    public long eval() {
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
