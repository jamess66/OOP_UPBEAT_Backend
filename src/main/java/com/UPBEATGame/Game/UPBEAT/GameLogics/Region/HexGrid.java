package com.UPBEATGame.Game.UPBEAT.GameLogics.Region;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Crew;
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

        grid[5][5].updateOwner(new Crew(this));
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

}
