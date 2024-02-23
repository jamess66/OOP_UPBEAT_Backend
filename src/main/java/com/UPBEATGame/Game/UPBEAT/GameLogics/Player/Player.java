package com.UPBEATGame.Game.UPBEAT.GameLogics.Player;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Map;
@JsonRootName(value = "info")
public interface Player {
    long getBudget();
    void payCost(long cost);
    void addBudget(long money);
    void updateCityCenter(Region to);
    void updateCurrentRegion(Region region);
    Region getCityCenter();
    Region getCurrentRegion();

}