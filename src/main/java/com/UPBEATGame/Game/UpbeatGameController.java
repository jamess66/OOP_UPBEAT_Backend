package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameState;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameUPBEAT;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UpbeatGameController {

    private static final GameUPBEAT gameUPBEAT = GameState.getGameInstance();
    private static int playerCount = 0;
    protected static int playerSubmittedCount = 0;
    private static int globalTurn = 0;

    @PostMapping({"/player"})
    public PlayerObject createPlayer(@RequestBody String body) {
        if(gameUPBEAT.getPlayers().isEmpty()) globalTurn = 1;
        PlayerObject playerObject = new PlayerObject(body, true, playerCount);
        playerCount++;
        return playerObject;
    }

    @GetMapping("/player/{name}")
    public PlayerObject getPlayer(@PathVariable String name){
        System.out.println(name);
        return new PlayerObject(name, false, gameUPBEAT.getPlayerInstance(name).getPlayerTurn());
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
            playerSubmittedCount = (playerSubmittedCount + 1) % gameUPBEAT.getPlayers().size() ;
            if(playerSubmittedCount == 0) globalTurn++;
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
            PlayerObject playerObject = new PlayerObject(playerInstance.getPlayerName(), false, playerInstance.getPlayerTurn());
            playerObject.setIsPlayerTurn();
            playerObjects.add(playerObject);
        }
        return playerObjects;
    }
}
