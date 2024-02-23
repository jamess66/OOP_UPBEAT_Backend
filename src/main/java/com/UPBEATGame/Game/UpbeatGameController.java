package com.UPBEATGame.Game;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameState;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameUPBEAT;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UpbeatGameController {

    private static final GameUPBEAT gameUPBEAT = GameState.getGameInstance();

    @PostMapping({"/player"})
    public String createPlayer(@RequestBody String body) {
        gameUPBEAT.createPlayerInstance(body);
        return "created " + body;
    }

    @GetMapping("/player/{name}")
    public PlayerObject getPlayer(@PathVariable String name){
        System.out.println(name);
        return new PlayerObject(name);
    }

    @PutMapping("/constructionPlan/{name}/{constructionPlan}")
    public Boolean submitConstructionPlan(@PathVariable String name, @PathVariable String constructionPlan){
        try {
            gameUPBEAT.getPlayerInstance(name).newConstructionPlan(constructionPlan);
            gameUPBEAT.getPlayerInstance(name).actionExecute();
        }catch (Exception e){
            return false; // construction plan is not correct syntax or player not found.
        }
        return true; // construction plan is correct syntax.
    }

    @GetMapping({"/territory"})
    public Territory getTerritory() {
        return gameUPBEAT.getTerritory();
    }
}
