package com.UPBEATGame.Game.UPBEAT.GameLogics.Region;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.*;

public interface Region {
    Player getOwner();
    float getDeposit();
    void updateDeposit(long amount);
    void updateDeposit(boolean instant, long amount);
    void updateOwner(Player owner);
    int getRegionX();
    int getRegionY();
}