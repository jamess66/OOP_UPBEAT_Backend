package GameLogics.Engine;

import Config.ConfigLoader;
import GameLogics.Engine.Commands.CrewCommands;
import GameLogics.Player.Crew;
import GameLogics.Region.HexGrid;
import GameLogics.Region.Territory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState implements GameUPBEAT {
    private static GameState gameInstance;
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

    public PlayerInstance createPlayerInstance(String name) {
        if(!players.containsKey(name)){
            players.put(name, new CrewCommands(new Crew(territory), territory));
        }
        return players.get(name);
    }

    public PlayerInstance createPlayerInstance(String name, int x, int y) { // fixed spawn position for test only
        if(!players.containsKey(name)){
            players.put(name, new CrewCommands(new Crew(territory, x, y), territory));
        }
        return players.get(name);
    }

    public PlayerInstance getPlayerInstance(String name) {
        return players.get(name);
    }

    public Territory getTerritory() {
        return territory;
    }
    public List<PlayerInstance> getPlayers(){
        return new ArrayList<>(players.values());
    }

}
