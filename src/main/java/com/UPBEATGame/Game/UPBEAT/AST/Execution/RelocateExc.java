package com.UPBEATGame.Game.UPBEAT.AST.Execution;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class RelocateExc extends Exec {
    @Override
    public boolean execute(PlayerInstance command) {
        command.relocate();
        return true;
    }
}