package com.UPBEATGame.Game.UPBEAT.GameData.Game;

import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;

import java.util.List;
import java.util.Map;

public interface GameInstance {
    PlayerInstance createPlayerInstance(String name, int x, int y); // fixed spawn position for test
    PlayerInstance getPlayerInstance(String name); // get player instance for test
    PlayerInstance createPlayerInstance(String name);
    boolean removePlayerInstance(String name);
    boolean isPlayerTurn(String name);
    Territory getTerritory();
    List<PlayerInstance> getPlayers();
    Map<String, PlayerInstance> getPlayersMap();
    boolean checkAnyLosePlayer();
    void checkIsAnyoneLoseCityCenter();
    int getTotalPlayer();
}
