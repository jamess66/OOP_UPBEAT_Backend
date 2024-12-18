package com.UPBEATGame.Game.UPBEAT.GameData.Game;

import com.UPBEATGame.Game.UPBEAT.AST.Node.Exec;
import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.ConsParser;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.Parser;
import com.UPBEATGame.Game.UPBEAT.ConstructionParser.Tokenizer.ConsTokenizer;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.Commands.GameCommands;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.Commands.IdentifierHashMap;
import com.UPBEATGame.Game.UPBEAT.GameData.Game.Commands.InformationExpression;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility;
import lombok.Data;
import lombok.Getter;

import java.util.*;

@Getter
@Data
public class CrewState implements PlayerInstance {
    private final String playerName;
    private final Player player;
    private int turn;
    private final Territory territory;
    private String constructionPlan;
    private long reserveTime;
    private long planTime;
    private List<Exec> parsedExec;
    private final Map<String, Long> boundVar;

    public CrewState(String playerName , Player player, Territory territory, int turn){
        this.reserveTime = (ConfigLoader.getPlan_rev_sec() * 1000) + (ConfigLoader.getPlan_rev_min() * 60000);
        this.planTime = (ConfigLoader.getInit_plan_sec() * 1000) + (ConfigLoader.getInit_plan_min() * 60000);
        this.playerName = playerName;
        this.player = player;
        this.territory = territory;
        this.turn = turn;
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
    public void setConstructionPlan(String string){
        if(!constructionPlan.equals(string)){
            System.out.println("changed");
            player.payCost(1);
        }
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
    public boolean done() {
        return GameCommands.done();
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
    public int getPlayerTurn(){
        return this.turn;
    }

    @Override
    public void setPlayerTurn(int turn){
       this.turn = turn;
    }

    @Override
    public Map<String, Long> getIdentifiers() {
        return boundVar;
    }

    @Override
    public void setReserveTime(long time){
        reserveTime = time;
    }

    @Override
    public long getReserveTime(){
        return reserveTime;
    }

    @Override
    public void setPlanTime(long time){
        planTime = time;
    }

    @Override
    public long getPlanTime(){
       return planTime;
    }
}
