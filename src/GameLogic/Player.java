package GameLogic;

import java.util.Objects;
import java.util.Random;

public class Player {
    private Region currentAt;

    public Player(Territory territory) {
        Random rand = new Random();
        int randx = rand.nextInt(territory.getTerritorySize()[0]);
        int randy = rand.nextInt(territory.getTerritorySize()[1]);
        currentAt = territory.getRegion(randx,randy);
    }

    public Player(Territory territory, int x, int y) {
        currentAt = territory.getRegion(x,y);
    }

    public void setCurrentRegion(Region region){
        currentAt = region;
    }

    public Region getCurrentAt() {
        return currentAt;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
