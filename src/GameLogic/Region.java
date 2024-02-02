package GameLogic;

public class Region {
    protected int x, y;

    protected int g, h;

    Region parent;

    protected Region(int x, int y) {
        this.x = x;
        this.y = y;
        g = h = 0;
        parent = null;
    }

    protected int f() {
        return g + h;
    }

    public int[] getIntPairRegionCoordinate() {
        return new int[]{x,y};
    }


}
