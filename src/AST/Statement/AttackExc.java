package AST.Statement;

import Models.Commands;
import Models.Utility.Direction;

import static AST.Node.*;

public class AttackExc extends Exec {
    private final Expr expression;
    private final Direction direction;

    public AttackExc(Expr expression, Direction direction) {
        this.expression = expression;
        this.direction = direction;
    }

    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform AttackExc " + direction + " " + expression);
        return command.attack(
                direction,
                expression.eval(command)
        );
    }
}