package GameLogics.Engine;

import GameLogics.Region.Territory;

import java.util.List;

public interface GameUPBEAT {
    PlayerInstance createPlayerInstance(String name, int x, int y); // fixed spawn position for test
    PlayerInstance getPlayerInstance(String name); // get player instance for test

    PlayerInstance createPlayerInstance(String name);
    Territory getTerritory();
    List<PlayerInstance> getPlayers();
}
