package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.GameDataInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.GameUPBEAT;
import com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UpbeatGameController {

    private static final GameUPBEAT gameUPBEAT = GameDataInstance.getGameInstance();
    private static int playerCount = 0;
    protected static int playerSubmittedCount = 0;
    private static int globalTurn = 1;
    private static final String[] colors = new String[]{"#ffc266", "#99d6ff", "#85e085", "#ff99dd", "#fbde6a", "#ff4d67", "#ffa07a"};

    @PostMapping({"/player"})
    public PlayerObject createPlayer(@RequestBody String body) {
        if(gameUPBEAT.getTerritory().isAllClaimed()) return null;
        //if(gameUPBEAT.getPlayers().isEmpty()) globalTurn = 1;
        PlayerObject playerObject = new PlayerObject(body, true, playerCount, colors[playerCount]);
        playerCount++;
        return playerObject;
    }

    @GetMapping("/player/{name}")
    public PlayerObject getPlayer(@PathVariable String name){
        System.out.println(name);
        return new PlayerObject(name, false, gameUPBEAT.getPlayerInstance(name).getPlayerTurn(), gameUPBEAT.getPlayerInstance(name).getPlayer().getColor());
    }

    @GetMapping("/globalTerns")
    public int getGlobalTerns(){
        return globalTurn;
    }

    @PutMapping("/constructionPlan/{name}/{constructionPlan}")
    public Boolean submitConstructionPlan(@PathVariable String name, @PathVariable String constructionPlan){
        try {
            if(gameUPBEAT.getPlayerInstance(name).getPlayerTurn() != playerSubmittedCount) return false;
            gameUPBEAT.getPlayerInstance(name).newConstructionPlan(constructionPlan);
            gameUPBEAT.getPlayerInstance(name).actionExecute();
            playerSubmittedCount = (playerSubmittedCount + 1) % gameUPBEAT.getPlayers().size();
            if(playerSubmittedCount == 0) globalTurn++;
            for (PlayerInstance playerInstance :gameUPBEAT.getPlayers()){
                if(playerInstance.getCityCenter() == null){
                    gameUPBEAT.getTerritory().removeLosePlayerRegion(playerInstance.getPlayer());
                    gameUPBEAT.getPlayersMap().remove(playerInstance.getPlayerName());
                }
            }

            if(gameUPBEAT.getPlayers().isEmpty()){
                playerCount = 0;
                playerSubmittedCount = 0;
                globalTurn = 1;
            }

        }catch (Exception e){
            return false; // construction plan is not correct syntax or player not found.
        }
        return true; // construction plan is correct syntax.
    }

    @GetMapping({"/territory"})
    public Territory getTerritory() {
        return gameUPBEAT.getTerritory();
    }

    @GetMapping({"/players"})
    public List<PlayerObject> getAllPlayer(){
        List<PlayerInstance> playerInstances = gameUPBEAT.getPlayers();
        List<PlayerObject> playerObjects = new ArrayList<>();
        for (PlayerInstance playerInstance: playerInstances){
            PlayerObject playerObject = new PlayerObject(playerInstance.getPlayerName(), false, playerInstance.getPlayerTurn(), playerInstance.getPlayer().getColor());
            playerObject.setIsPlayerTurn();
            playerObjects.add(playerObject);
        }
        return playerObjects;
    }
}
