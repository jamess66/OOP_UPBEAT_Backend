package com.UPBEATGame.Game.UPBEAT.GameData.Region;

import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;

public interface Territory {
    Region getRegion(int row, int col);
    long getRows();
    long getCols();
    Region[][] getGrid();

    void removeLosePlayerRegion(Player player);

    boolean isAllClaimed();
}
