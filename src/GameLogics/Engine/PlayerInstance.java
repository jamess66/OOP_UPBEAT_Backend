package GameLogics.Engine;

import GameLogics.Region.Region;

import java.util.Map;

import GameLogics.Region.Territory;
import GameLogics.Utility.Direction;

public interface PlayerInstance {
    
    boolean attack(Direction dir, long v);
    boolean collect(long v);
    boolean invest(long eval);
    boolean relocate();
    long nearby(Direction dir);
    boolean move(Direction dir);
    long opponent();
    long getCurrentRow();
    long getCurrentCol();
    long getCityCenterRow();
    long getCityCenterCol();
    long getBudget();
    long getRandomVal();
    Territory getTerritory();
    Region getCurrentRegion();
    Region getCityCenter();
    Map<String, Long> getIdentifiers();
    void newConstructionPlan(String string);
    void newConstructionPlan();
    void actionExecute();


}
