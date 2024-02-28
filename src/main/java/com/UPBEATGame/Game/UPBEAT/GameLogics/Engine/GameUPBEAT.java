package com.UPBEATGame.Game.UPBEAT.GameLogics.Engine;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;

import java.util.List;
import java.util.Map;

public interface GameUPBEAT {
    PlayerInstance createPlayerInstance(String name, int x, int y, String color); // fixed spawn position for test
    PlayerInstance getPlayerInstance(String name); // get player instance for test
    PlayerInstance createPlayerInstance(String name, int turn, String color);
    Territory getTerritory();
    List<PlayerInstance> getPlayers();

    Map<String, PlayerInstance> getPlayersMap();
}
