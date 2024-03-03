package com.UPBEATGame.Game.UPBEAT.GameData.Player;

import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "info")
public interface Player {
    long getBudget();
    void payCost(long cost);
    void addBudget(long money);
    void updateCityCenter(Region to);
    void updateCurrentRegion(Region region);
    Region getCityCenter();
    Region getCurrentRegion();
    String getColor();

}