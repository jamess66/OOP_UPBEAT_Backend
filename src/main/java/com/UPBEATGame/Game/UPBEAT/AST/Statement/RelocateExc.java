package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;

import static com.UPBEATGame.Game.UPBEAT.AST.Node.*;

public class RelocateExc extends Exec {
    @Override
    public boolean execute(PlayerInstance command) {
        command.relocate();
        return true;
    }
}