package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.PlayerInstance;

import java.util.Map;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class AssignmentExc extends Exec {
    private final String identifier;
    private final Expr expression;

    public AssignmentExc(String identifier, Expr expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public boolean execute(PlayerInstance command) {
        Map<String, Long> identifiers = command.getIdentifiers();
        if(!identifiers.containsKey(identifier)){
            identifiers.put(identifier, 0L);
        }
        identifiers.put(identifier, expression.eval(command));
        if(next != null) return next.execute(command);
        return true;
    }
}