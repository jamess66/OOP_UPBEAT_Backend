package com.UPBEATGame.Game.UPBEAT.GameLogics.Region;

public interface Territory {
    Region getRegion(int row, int col);
    long getRows();
    long getCols();
    Region[][] getGrid();
}
