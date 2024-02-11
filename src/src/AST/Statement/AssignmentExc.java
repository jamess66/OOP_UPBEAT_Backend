package src.AST.Statement;

import Models.Command;

import java.util.Map;

import static AST.Node.*;

public class AssignmentExc extends Exec {
    private final String identifier;
    private final Expr expression;

    public AssignmentExc(String identifier, Expr expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public boolean execute(Command gamecmd) {
        System.out.println("Perform AssignmentExc");
        Map<String, Long> memory = gamecmd.getIdentifiers();
        memory.put(identifier, expression.eval(gamecmd));
        return next.execute(gamecmd);
    }
}