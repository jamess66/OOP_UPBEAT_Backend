package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.AST.Expression.NumberExp;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;

import java.util.ArrayList;
import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class WhileExc extends Exec {
    private int executionRemain = 10000;
    public Expr condition;
    public Exec statement;

    public WhileExc(Expr condition, Exec statements) {
        this.condition = condition;
        this.statement = statements;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        while (condition.eval(command) > 0 && executionRemain >= 0){
            IfExc ifExc = new IfExc(new NumberExp(executionRemain), statement, new BlockExc(new ArrayList<>()));
            ifExc.execute(command);
            executionRemain--;
        }
        if(next != null) return next.execute(command);
        else return false;
    }
}