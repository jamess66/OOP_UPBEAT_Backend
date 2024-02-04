package HexWorld;

import Config.ConfigLoader;

import java.util.Random;
import java.util.Properties;

import static Config.ConfigLoader.INIT_BUDGET;

public class Player {

    private Region currentAt;
    private Region cityCenter;
    private int budget;

    public Player(Territory territory) {
        this.budget = INIT_BUDGET;

        Random rand = new Random();
        int randx = rand.nextInt(territory.getTerritorySize()[0]);
        int randy = rand.nextInt(territory.getTerritorySize()[1]);
        spawn(territory, randx, randy);
    }

    public Player(Territory territory, int x, int y) {
        this.budget = INIT_BUDGET;
        spawn(territory, x, y);
    }

    public int wallet(){
        return budget;
    }

    public void pay(int cost){
        int remain = budget - cost;
        budget = Math.max(remain, 0);
    }

    private void spawn(Territory territory, int x, int y){
        Region region = territory.getRegion(x,y);
        currentAt = region;
        cityCenter = region;
        region.claim(this);
    }

    public void setCurrentRegion(Region region){
        currentAt = region;
    }

    public Region getCurrentAt() {
        return currentAt;
    }
    public Region getCityCenter() {
        return cityCenter;
    }
    public void moveCityCenter(Region newCityCenter) {
        cityCenter = newCityCenter;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return super.equals(obj);
    }
}
