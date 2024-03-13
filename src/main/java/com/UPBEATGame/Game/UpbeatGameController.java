package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.GameState;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.GameInstance;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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

    @DeleteMapping("/removePlayer/{name}")
    public boolean removePlayer(@PathVariable String name) {
        if (getPlayer(name).getIsPlayerTurn()){
            submitConstructionPlan(name, "done");
        }
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
        System.out.println("Put Construction Plan.");
        try {
            if(GAME_INSTANCE.getPlayerInstance(name).getPlayerTurn() != currentPlayerSubmittedTurn) return "Not your turn yet!!"; // not player turn
            GAME_INSTANCE.getPlayerInstance(name).setConstructionPlan(constructionPlan);
            GAME_INSTANCE.getPlayerInstance(name).actionExecute();
            turnCalculation();
            if(GAME_INSTANCE.getPlayers().isEmpty() || GAME_INSTANCE.getPlayers().size() <= 1){
                currentPlayerSubmittedTurn = 0;
                globalTurn = 0;
            }

            GAME_INSTANCE.checkIsAnyoneLoseCityCenter();

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
        System.out.println("Get Territory.");
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
        System.out.println("Get All Player.");
        return playerObjects;
    }

    @PutMapping("/updateReserveTime/{name}")
    public String updateReserveTime(@PathVariable String name, @RequestBody String time){
        try {
            GAME_INSTANCE.getPlayerInstance(name).setReserveTime(Long.parseLong(time));
            System.out.println("Updated " + name + " Reserve Time.");
            return "set";
        }catch (Exception e){
            return e.getMessage();
        }

    }


    @PutMapping("/updatePlanTime/{name}")
    public String updatePlanTime(@PathVariable String name, @RequestBody String time){
        try {
            GAME_INSTANCE.getPlayerInstance(name).setPlanTime(Long.parseLong(time));
            System.out.println("Updated " + name + " Reserve Time.");
            return "set";
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @GetMapping("/times/{name}")
    public PlanTime getConfigurationTime(@PathVariable String name){
        long planTime = GAME_INSTANCE.getPlayerInstance(name).getPlanTime();
        long reserveTime = GAME_INSTANCE.getPlayerInstance(name).getReserveTime();;
        return PlanTime.builder()
                .initPlanTime((ConfigLoader.getInit_plan_sec() * 1000) + (ConfigLoader.getInit_plan_min() * 60000))
                .planTime(planTime)
                .reserveTime(reserveTime)
                .build();
    }

    @GetMapping("/isPlayerTurn/{name}")
    public boolean isPlayerTurn(@PathVariable String name){
        return getPlayer(name).getIsPlayerTurn();
    }


    @GetMapping({"/check"})
    public String checkApi(){
        return "It's working";
    }

    @Getter
    @Builder
    private static class PlanTime {
        long initPlanTime;
        long planTime;
        long reserveTime;
    }
}
