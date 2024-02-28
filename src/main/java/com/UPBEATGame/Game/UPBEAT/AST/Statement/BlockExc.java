package com.UPBEATGame.Game.UPBEAT.AST.Statement;

import com.UPBEATGame.Game.UPBEAT.AST.Node.Exec;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;

import java.util.List;

public class BlockExc extends Exec {
    private final List<Exec> nodes;

    public BlockExc(List<Exec> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean execute(PlayerInstance command) {
        for(Exec node : nodes){
            if(!node.execute(command)) return false;
        }
        return true;
    }
}
