package Models.Player;

import Config.ConfigLoader;
import Models.Region.Region;
import Models.Region.Territory;
import Models.Utility;

import java.util.HashMap;
import java.util.Map;

public class Crew implements Player{
    private final String name;
    private long budget;
    private Region CityCenter;
    private Region currRegion;
    private final Map<String, Long> identifier;
    private final Territory territory;
    public Crew(Territory territory, String name) {
        this.name = name;
        this.territory = territory;
        this.budget = ConfigLoader.getInit_budget();
        randomSpawn(territory);
        this.identifier = new HashMap<>();
    }

    public Crew(Territory territory, int x, int y, String name){
        this.name = name;
        this.territory = territory;
        this.budget = ConfigLoader.getInit_budget();
        Region region = territory.getRegion(x,y);
        this.currRegion = region;
        this.CityCenter = region;
        this.CityCenter.updateDeposit(true , ConfigLoader.init_center_dep);
        region.updateOwner(this);
        this.identifier = new HashMap<>();
    }

    private void randomSpawn(Territory territory){
        Region region = Utility.getRandomRegion(territory);
        region.updateOwner(this);
        this.CityCenter = region;
        this.currRegion = region;
        this.CityCenter.updateDeposit(true ,ConfigLoader.init_center_dep);
    }




    public static void main(String[] args) {
        System.out.println(Utility.getRandomNumber(ConfigLoader.getRows()));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getBudget() {
        return this.budget;
    }

    @Override
    public void payCost(long cost) {
        addBudget(-1 * cost);
    }

    @Override
    public void addBudget(long budget) {
        this.budget = Math.max(0, this.budget + budget);
    }

    @Override
    public Region getCityCenter() {
        return this.CityCenter;
    }

    @Override
    public void updateCurrentRegion(Region region){
        currRegion = region;
    }
    @Override
    public Region getCurrentRegion(){
        return currRegion;
    }
    @Override
    public Map<String, Long> getIdentifiers() {
        return this.identifier;
    }

    @Override
    public void updateCityCenter(Region target) {
        CityCenter = target;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null ) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return super.equals(player);
    }
}
