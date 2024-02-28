package com.UPBEATGame.Game.UPBEAT.GameLogics.Region;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;

public interface Territory {
    Region getRegion(int row, int col);
    long getRows();
    long getCols();
    Region[][] getGrid();

    void removeLosePlayerRegion(Player player);

    boolean isAllClaimed();
}
