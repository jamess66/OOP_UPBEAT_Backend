package com.UPBEATGame.Game.UPBEAT.GameData.Game;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Crew;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.HexGrid;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GameState implements GameInstance {
    private static GameState gameInstance = new GameState();
    private final Map<String, PlayerInstance> players;
    private final Territory territory;

    private static final List<String> PlayerColorList = new ArrayList<>();
    private static int totalPlayer = 0;

    static {
        PlayerColorList.addAll(List.of(
                "#FF6D6A", "#99d6ff", "#85e085", "#ff99dd", "#fbde6a", "#f2a3af", "#ffa07a", "#914d4d", "#8e6d50",
                "#878e50", "#508f5b", "#50828f", "#50538f", "#71508f", "#8f507f", "#443334", "#4c4c4a", "#cecece"
        ));
    }

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
        System.out.println("createPlayerInstance " + name);
        if(!players.containsKey(name)){
            players.put(name, new CrewState(name, new Crew(territory, randomPlayerColor()), territory, setPlayerTurn()));
            totalPlayer = totalPlayer + 1;
        }
        return players.get(name);
    }

    public boolean removePlayerInstance(String name){
        if(!players.containsKey(name)) return false;
        PlayerInstance playerToRemove = players.get(name);
        players.remove(name);
        for (PlayerInstance playerInstance: players.values()){
            if(playerInstance.getPlayerTurn() > playerToRemove.getPlayerTurn()){
                playerInstance.setPlayerTurn(playerInstance.getPlayerTurn() - 1);
            }
        }
        territory.removeLosePlayerRegion(playerToRemove.getPlayer());
        PlayerColorList.add(playerToRemove.getPlayer().getPlayerColor()); // add player color back to reuse
        totalPlayer = totalPlayer - 1;
        return true;
    }

    @Override
    public boolean isPlayerTurn(String name) {
        return false;
    }


    public PlayerInstance createPlayerInstance(String name, int x, int y) { // fixed spawn position for test only
        if(!players.containsKey(name)){
            players.put(name, new CrewState(name, new Crew(territory, x, y, randomPlayerColor()), territory, 0));
        }
        return players.get(name);
    }

    public PlayerInstance getPlayerInstance(String name) {
        if(!players.containsKey(name)) return null;
        return players.get(name);
    }

    public List<PlayerInstance> getPlayers(){
        return new ArrayList<>(players.values());
    }

    public Map<String, PlayerInstance> getPlayersMap(){
        return players;
    }

    @Override
    public boolean checkAnyLosePlayer() {
        boolean isAnyLosePlayer = false;
        for (PlayerInstance playerInstance : getPlayers()){
            if(playerInstance.getCityCenter() == null){
                removePlayerInstance(playerInstance.getPlayerName());
                isAnyLosePlayer = true;
            }
        }
        return isAnyLosePlayer;
    }

//    @Override
//    public boolean isGameOver(){
//        if(players.size() == 1) return true;
//        return false;
//    }

    private String randomPlayerColor() {
        int randIndex = (int) Math.abs(Utility.getRandomNumber() % PlayerColorList.size());
        String randColor = PlayerColorList.get(randIndex);
        PlayerColorList.remove(randIndex);
        return randColor;
    }


    private int setPlayerTurn(){
        return totalPlayer;
    }

    public void checkIsAnyoneLoseCityCenter(){

        for (PlayerInstance player : players.values()){
            if(player.getCityCenter().getOwner() == null){
                player.getPlayer().updateCityCenter(null);
            }
        }
    }
}
