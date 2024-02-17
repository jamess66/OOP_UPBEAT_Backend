package Models.Region;

import Config.ConfigLoader;

import java.util.Map;

public class HexGrid implements Territory{
    private final Region[][] grid;

    private final int rows, cols;

    public HexGrid(){
        rows = (int) ConfigLoader.rows;
        cols = (int) ConfigLoader.cols;
        grid = new Region[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new HexRegion(i, j);
            }

        }
    }


    @Override
    public Region getRegion(long row, long col) {
        return grid[(int) row][(int) col];
    }

    @Override
    public long getRows(){
        return rows;
    }

    @Override
    public long getCols(){
        return cols;
    }

}
