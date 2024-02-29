package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.AST.Node.*;
import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.PlayerInstance;

public class IfExc extends Exec {
    protected final Expr condition;
    protected Exec thenExec;
    protected Exec elseExec;

    public IfExc(Expr condition, Exec thenExec, Exec elseExec) {
        this.condition = condition;
        this.thenExec = thenExec;
        this.elseExec = elseExec;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        thenExec.next = next;
        elseExec.next = next;
        if(condition.eval(command) > 0){
            return thenExec.execute(command);
        } else {
            return elseExec.execute(command);
        }
    }
}