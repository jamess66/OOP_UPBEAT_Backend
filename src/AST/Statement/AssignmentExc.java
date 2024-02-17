package AST.Statement;

import Models.Commands;

import java.util.Map;

import static AST.Node.*;

public class AssignmentExc extends Exec {
    private final String identifier;
    private final Expr expression;

    public AssignmentExc(String identifier, Expr expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public boolean execute(Commands command) {
        System.out.println("Perform AssignmentExc");
        Map<String, Long> identifiers = command.getIdentifiers();
        identifiers.put(identifier, expression.eval(command));
        return next.execute(command);
    }
}