package com.UPBEATGame.Game.UPBEAT.GameLogics.Engine;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.CrewCommands;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Crew;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.HexGrid;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GameState implements GameUPBEAT {
    private static GameState gameInstance = new GameState();
    private final Map<String, PlayerInstance> players;
    private final Territory territory;

    private GameState() {
        territory = new HexGrid((int) ConfigLoader.getRows(), (int) ConfigLoader.getCols());
        players = new HashMap<>();
    }

    public static GameState getGameInstance() {
        if(gameInstance == null){
            gameInstance = new GameState();
        }
        return gameInstance;
    }

    public PlayerInstance createPlayerInstance(String name, int tern, String color) {
        System.out.println("createPlayerInstance " + name);
        if(!players.containsKey(name)){
            players.put(name, new CrewCommands(name, new Crew(territory, color), territory, tern));
        }
        return players.get(name);
    }

    public PlayerInstance createPlayerInstance(String name, int x, int y, String color) { // fixed spawn position for test only
        if(!players.containsKey(name)){
            players.put(name, new CrewCommands(name, new Crew(territory, x, y, color), territory, 0));
        }
        return players.get(name);
    }

    public PlayerInstance getPlayerInstance(String name) {
        return players.get(name);
    }

    public List<PlayerInstance> getPlayers(){
        return new ArrayList<>(players.values());
    }

    public Map<String, PlayerInstance> getPlayersMap(){
        return players;
    }

}
