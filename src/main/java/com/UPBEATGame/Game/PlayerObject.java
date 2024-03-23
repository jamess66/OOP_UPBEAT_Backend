package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.GameState;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import java.util.Map;

import static com.UPBEATGame.Game.UpbeatGameController.currentPlayerSubmittedTurn;

@Getter
@JsonRootName(value = "crew")
public class PlayerObject {
    private String playerName;
    private Map<String, Long> identifier;
    private Player crewInfo;
    private Boolean isPlayerTurn;
    //@JsonIgnore
    private int playerTurn;

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
        playerTurn = playerInstance.getPlayerTurn();
        setIsPlayerTurn();
    }

    public void setIsPlayerTurn(){
        this.isPlayerTurn = currentPlayerSubmittedTurn == playerTurn;
    }

}
