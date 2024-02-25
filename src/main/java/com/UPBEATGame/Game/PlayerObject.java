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
    private long playerHashCode;
    private Player crewInfo;


    public PlayerObject(String playerName, boolean createNew) {
        PlayerInstance playerInstance;
        if (createNew) {
            playerInstance = GameState.getGameInstance().createPlayerInstance(playerName);
        }else {
            playerInstance = GameState.getGameInstance().getPlayerInstance(playerName);
        }
        this.playerName = playerInstance.getPlayerName();
        identifier = playerInstance.getIdentifiers();
        crewInfo = playerInstance.getPlayer();
        playerHashCode = crewInfo.hashCode();
    }

}
