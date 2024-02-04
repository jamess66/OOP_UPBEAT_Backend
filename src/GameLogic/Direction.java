package GameLogic;

public class Direction {
    protected static final int[][][] DIRECTION = new int[][][]{
            {{-1, 0}, {0, +1}, {+1, +1}, {+1, 0}, {+1, -1}, {0, -1}}, // even row
            {{-1, 0}, {-1, +1}, {0, +1}, {+1, 0}, {0, -1}, {-1, -1}}, // odd row
    };
}
