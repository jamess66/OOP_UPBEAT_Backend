package com.UPBEATGame.Game.UPBEAT.GameLogics.Player;

import com.UPBEATGame.Game.UPBEAT.AST.Node.Exec;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.ConsParser;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.Parser;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer.ConsTokenizer;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.Commands.IdentifierHashMap;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.Commands.InformationExpression;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameCommands;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility;
import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.util.*;

import static com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer.ReadConstructionPlan.forTestingConstructionPlan;

@Getter
@Data
public class CrewCommands implements PlayerInstance {
    private final String playerName;
    private final Player player;
    private final Territory territory;
    private String constructionPlan;
    private List<Exec> parsedExec;
    private final Map<String, Long> boundVar;
    public CrewCommands(String playerName , Player player, Territory territory){
        this.playerName = playerName;
        this.player = player;
        this.territory = territory;
        constructionPlan = "done";
        Parser parser = new ConsParser(new ConsTokenizer(constructionPlan));
        parsedExec = parser.Parse();
        boundVar = new IdentifierHashMap();
    }

    @Override
    public void actionExecute(){
        Parser parser = new ConsParser(new ConsTokenizer(constructionPlan));
        parsedExec = parser.Parse();
        boolean isExecutable = true;
        for(Exec exec : parsedExec){
            if(isExecutable){
                isExecutable = exec.execute(this);
            }
        }
    }

    @Override
    public void newConstructionPlan(){
        try {
            constructionPlan = forTestingConstructionPlan();
        } catch (IOException e) {
            constructionPlan = "";
        }
    }

    @Override
    public void newConstructionPlan(String string){
            constructionPlan = string;
    }

    @Override
    public boolean attack(Utility.Direction dir, long v) {
        return GameCommands.shoot(territory, player, dir, v);
    }

    @Override
    public boolean collect(long v) {
        return GameCommands.collect(player, v);
    }

    @Override
    public boolean invest(long eval) {
        return GameCommands.invest(territory, player, eval);
    }

    @Override
    public boolean relocate() {
        return GameCommands.relocate(player);
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }
    @Override
    public long nearby(Utility.Direction dir) {
        return InformationExpression.nearby(territory, player, dir);
    }

    @Override
    public boolean move(Utility.Direction dir) {
        return GameCommands.move(territory, player, dir);
    }

    @Override
    public long opponent() {
        return InformationExpression.opponent(territory, player);
    }

    @Override
    public long getCurrentRow() {
        return player.getCurrentRegion().getRegionX();
    }

    @Override
    public long getCurrentCol() {
        return player.getCurrentRegion().getRegionY();
    }

    @Override
    public long getCityCenterRow() {
        return player.getCityCenter().getRegionX();
    }

    @Override
    public long getCityCenterCol() {
        return player.getCityCenter().getRegionY();
    }

    @Override
    public long getBudget() {
        return player.getBudget();
    }

    @Override
    public long getRandomVal() {
        Random random = new Random();
        return random.nextLong(0,999) ;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Territory getTerritory() {
        return territory;
    }

    @Override
    public Region getCurrentRegion() {
        return player.getCurrentRegion();
    }

    @Override
    public Region getCityCenter() {
        return player.getCityCenter();
    }

    @Override
    public Map<String, Long> getIdentifiers() {
        return boundVar;
    }

}
