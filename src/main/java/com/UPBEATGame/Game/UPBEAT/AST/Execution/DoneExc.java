package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class DoneExc extends Exec {
    @Override
    public boolean execute(PlayerInstance command) {
        return command.done();
    }
}