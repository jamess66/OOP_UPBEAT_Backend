package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameState;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameUPBEAT;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Crew;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.CrewCommands;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.HexGrid;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.HexRegion;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonRootName(value = "crew")
public class PlayerObject {
    private String playerName;
    private Map<String, Long> identifier;
    private Player crewInfo;


    public PlayerObject(String playerName) {
        PlayerInstance p = GameState.getGameInstance().getPlayerInstance(playerName);
        if(p == null){
            this.playerName = "Not created yet.";
            return;
        }
        this.playerName = playerName;
        identifier = p.getIdentifiers();
        crewInfo = p.getPlayer();
    }

}
