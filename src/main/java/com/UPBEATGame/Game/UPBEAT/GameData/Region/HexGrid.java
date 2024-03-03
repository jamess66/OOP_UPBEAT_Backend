package com.UPBEATGame.Game.UPBEAT.GameData.Region;

import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import lombok.Getter;

@Getter
public class HexGrid implements Territory{
    private final Region[][] grid;

    private final int rows, cols;

    public HexGrid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Region[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new HexRegion(i, j);

            }

        }
    }


    @Override
    public Region getRegion(int row, int col) {
        return grid[row][col];
    }

    @Override
    public long getRows(){
        return rows;
    }

    @Override
    public long getCols(){
        return cols;
    }

    @Override
    public Region[][] getGrid(){
        return grid;
    }

    @Override
    public void removeLosePlayerRegion(Player player) {
        for (Region[] regions : grid) {
            for (Region region : regions) {
                if (player.equals(region.getOwner())) {
                    region.updateOwner(null);
                    region.updateDeposit(true, 0);
                }
            }
        }
    }

    @Override
    public boolean isAllClaimed() {
        for (Region[] regions : grid) {
            for (Region region : regions) {
                if (region.getOwner() == null) {
                   return false;
                }
            }
        }
        return true;
    }

}
