package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameState;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
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
    @JsonIgnore
    private int playerTurn;




    @JsonIgnore

    public PlayerObject(String playerName, boolean createNew, int count) {
        PlayerInstance playerInstance;
        if (createNew) {
            playerInstance = GameState.getGameInstance().createPlayerInstance(playerName, count);
        }else {
            playerInstance = GameState.getGameInstance().getPlayerInstance(playerName);
        }
        this.playerName = playerInstance.getPlayerName();
        identifier = playerInstance.getIdentifiers();
        crewInfo = playerInstance.getPlayer();
        playerHashCode = crewInfo.hashCode();
        playerTurn = playerInstance.getPlayerTurn();
        setIsPlayerTurn();
    }

    void setIsPlayerTurn(){
        this.isPlayerTurn = playerSubmittedCount == playerTurn;
    }

}
