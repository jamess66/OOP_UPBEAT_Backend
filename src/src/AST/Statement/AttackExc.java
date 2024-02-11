package src.AST.Statement;

import Models.Command;
import Models.Direction;

import static AST.Node.*;

public class AttackExc extends Exec {
    private final Expr expression;
    private final Direction direction;

    public AttackExc(Expr expression, Direction direction) {
        this.expression = expression;
        this.direction = direction;
    }

    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform AttackExc " + direction + " " + expression);
        return gamecmd.attack(
                direction,
                expression.eval(gamecmd)
        );
    }
}