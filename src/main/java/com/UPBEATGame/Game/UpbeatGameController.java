package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.GameState;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.GameInstance;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class UpbeatGameController {

    private static final GameInstance GAME_INSTANCE = GameState.getGameInstance();
    protected static int currentPlayerSubmittedTurn = 0;
    private static int globalTurn = 0;


    @PostMapping({"/player"})
    public PlayerObject createPlayer(@RequestBody String body) {
        if(GAME_INSTANCE.getTerritory().isAllClaimed()) return null; // cannot create anymore
        return new PlayerObject(body, true);
    }

    @DeleteMapping("/player/{name}")
    public boolean removePlayer(@PathVariable String name) {
        return GAME_INSTANCE.removePlayerInstance(name);
    }

    @GetMapping("/player/{name}")
    public PlayerObject getPlayer(@PathVariable String name){
        return new PlayerObject(name, false);
    }

    @GetMapping("/globalTerns")
    public int getGlobalTerns(){
        return globalTurn;
    }

    @PutMapping("/constructionPlan/{name}")
    public String submitConstructionPlan(@PathVariable String name, @RequestBody String constructionPlan){
        try {
            if(GAME_INSTANCE.getPlayerInstance(name).getPlayerTurn() != currentPlayerSubmittedTurn) return "Not your turn yet!!"; // not player turn
            GAME_INSTANCE.getPlayerInstance(name).setConstructionPlan(constructionPlan);
            GAME_INSTANCE.getPlayerInstance(name).actionExecute();
            turnCalculation();
            if(GAME_INSTANCE.getPlayers().isEmpty()){
                currentPlayerSubmittedTurn = 0;
                globalTurn = 0;
            }

        }catch (Exception e){
            return "Error: " + e.getMessage();
        }
        return "Finished!!"; // construction plan is correct syntax.
    }

    private void turnCalculation(){
        if(GAME_INSTANCE.checkAnyLosePlayer()) return;
        currentPlayerSubmittedTurn = (currentPlayerSubmittedTurn + 1) % GAME_INSTANCE.getPlayers().size();
        if(currentPlayerSubmittedTurn == 0) globalTurn++;
    }

    @GetMapping({"/territory"})
    public Territory getTerritory() {
        return GAME_INSTANCE.getTerritory();
    }

    @GetMapping({"/players"})
    public List<PlayerObject> getAllPlayer(){
        List<PlayerInstance> playerInstances = GAME_INSTANCE.getPlayers();
        List<PlayerObject> playerObjects = new ArrayList<>();
        for (PlayerInstance playerInstance: playerInstances){
            PlayerObject playerObject = new PlayerObject(playerInstance.getPlayerName(), false);
            playerObject.setIsPlayerTurn();
            playerObjects.add(playerObject);
        }
        return playerObjects;
    }
}
