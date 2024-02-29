package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.GameDataInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.PlayerInstance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import java.util.Map;

import static com.UPBEATGame.Game.UpbeatGameController.playerSubmittedCount;

@Getter
@JsonRootName(value = "crew")
public class PlayerObject {
    private String playerName;
    private Map<String, Long> identifier;
    private long playerHashCode;
    private Player crewInfo;
    private Boolean isPlayerTurn;
    private String color;
    @JsonIgnore
    private int playerTurn;

    @JsonIgnore

    public PlayerObject(String playerName, boolean createNew, int count, String color) {
        PlayerInstance playerInstance;
        if (createNew) {
            playerInstance = GameDataInstance.getGameInstance().createPlayerInstance(playerName, count, color);
        }else {
            playerInstance = GameDataInstance.getGameInstance().getPlayerInstance(playerName);
        }
        this.playerName = playerInstance.getPlayerName();
        identifier = playerInstance.getIdentifiers();
        crewInfo = playerInstance.getPlayer();
        playerHashCode = crewInfo.hashCode();
        playerTurn = playerInstance.getPlayerTurn();
        setIsPlayerTurn();
        this.color = color;
    }

    void setIsPlayerTurn(){
        this.isPlayerTurn = playerSubmittedCount == playerTurn;
    }

}
