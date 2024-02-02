package GameLogic;

import java.util.Random;

public class Player {
    private String name = "";
    private int currentX = 0;
    private int currentY = 0;

    private WorldMap worldMap;

    public Player(String name, WorldMap worldMap) {
        this.name = name;
        this.worldMap = worldMap;
    }

    public void spawn() {
        Random rand = new Random();
        currentX = rand.nextInt(worldMap.width);
        currentY = rand.nextInt(worldMap.height);
    }

    public int[] position(){
        return new int[]{currentX,currentY};
    }

    public Region atRegion(){
        return worldMap.getRegion(currentX, currentY);
    }
}
