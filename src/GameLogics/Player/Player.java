package GameLogics.Player;

import GameLogics.Region.Region;

import java.util.Map;

public interface Player {
    long getBudget();
    void payCost(long cost);
    void addBudget(long money);
    void updateCityCenter(Region to);
    void updateCurrentRegion(Region region);
    Region getCityCenter();
    Region getCurrentRegion();
    Map<String,Long> getIdentifiers();
}