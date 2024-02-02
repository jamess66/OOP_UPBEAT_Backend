package GameLogic;

import java.util.Arrays;

public class WorldMap {
    protected final int width, height;

    private final Region[][] worldRegions;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        worldRegions = new Region[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                worldRegions[i][j] = new Region(i, j);
            }
        }
    }

    public Region[][] getWorldRegions() {
        return worldRegions;
    }

    public Region getRegion(int posX, int posY) {
        return worldRegions[posX][posY];
    }
    public void printWorldCoordinate() {
        for (int i = 0; i < height; i++) {
            if(i % 2 == 1) System.out.print("   ");
            for (int j = 0; j < width; j++)
                System.out.print(Arrays.toString(worldRegions[j][i].getIntPairRegionCoordinate()) + " ");
            if(i % 2 == 0) System.out.print("");
            System.out.println();
        }
    }
}
