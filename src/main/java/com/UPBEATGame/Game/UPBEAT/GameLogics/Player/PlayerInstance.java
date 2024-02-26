package com.UPBEATGame.Game.UPBEAT.GameLogics.Player;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;

import java.util.Map;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.Direction;

public interface PlayerInstance {
    Player getPlayer();
    Territory getTerritory();
    Region getCurrentRegion();
    Region getCityCenter();
    long getBudget();
    String getPlayerName();
    boolean done();
    boolean attack(Direction dir, long v);
    boolean collect(long v);
    boolean invest(long eval);
    boolean relocate();
    long nearby(Direction dir);
    boolean move(Direction dir);
    long opponent();
    long getCurrentRow();
    long getCurrentCol();
    long getCityCenterRow();
    long getCityCenterCol();
    long getRandomVal();
    Map<String, Long> getIdentifiers();
    void newConstructionPlan(String string);
    void newConstructionPlan();
    void actionExecute();
    int getPlayerTurn();
}
