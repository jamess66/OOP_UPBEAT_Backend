package AST.Statement;

import GameLogics.Engine.PlayerInstance;

import java.util.Map;

import static AST.Node.*;

public class AssignmentExc extends Exec {
    private final String identifier;
    private final Expr expression;

    public AssignmentExc(String identifier, Expr expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public boolean execute(PlayerInstance command) {
        Map<String, Long> identifiers = command.getIdentifiers();
        identifiers.put(identifier, expression.eval(command));
        if(next != null) return next.execute(command);
        return true;
    }
}