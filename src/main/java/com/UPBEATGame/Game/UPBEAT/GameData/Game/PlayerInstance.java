package com.UPBEATGame.Game.UPBEAT.GameData.Game;

import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;

import java.util.Map;

import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility.Direction;

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
    void setConstructionPlan(String string);

    void actionExecute();
    int getPlayerTurn();
    void setPlayerTurn(int turn);
    void setReserveTime(long time);
    void setPlanTime(long time);
    long getReserveTime();
    long getPlanTime();
}
