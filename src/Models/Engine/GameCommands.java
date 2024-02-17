package Models.Engine;
import Models.Player.Player;
import Models.Region.Region;
import Models.Region.Territory;
import Models.Utility;
import Models.Utility.Direction;

import java.util.List;

import static Models.Utility.getRegionInDirection;
import static Models.Utility.isPlayerOwner;


public class GameCommands {

    static boolean done(){
        return true;
    }

    public static boolean relocate(Player player){
        Region currentRegion = player.getCurrentRegion();
        Region currentCenterCity = player.getCityCenter();
        long cost = (5 * Utility.getDistance(currentRegion, currentCenterCity)) + 10;
        if(player.getBudget() >= cost){
            if(currentRegion.getOwner() != null && isPlayerOwner(player,currentRegion)){ // cannot relocate if the region is opponent region but still pay cost.
                player.updateCityCenter(currentRegion);
                player.payCost(cost);
                return true;
            }
            player.payCost(cost);
        }
        return false;
    }

    public static boolean move(Territory territory, Player player, Direction direction){
        if(player.getBudget() >= 1){
            Region current = player.getCurrentRegion();
            int[] newXY = calculateNewXY(direction, current);
            if(Utility.isValidCoordinate(newXY[0], newXY[1])){ // do nothing if move to edge of the map
                Region newRegion = territory.getRegion(newXY[0], newXY[1]);
                if(newRegion.getOwner() == null ) player.updateCurrentRegion(newRegion);
                else if(isPlayerOwner(player,newRegion)) player.updateCurrentRegion(newRegion);
                else System.out.println("enemy");
            }
            player.payCost(1);
            return true;
        }
        return false;// once execute turn end
    }

    public static boolean invest(Territory territory , Player player, long investment){
        Region region = player.getCurrentRegion();
        long cost = investment + 1;
        if(player.getBudget() >= cost){
            if(isPlayerOwner(player,region)) {
                player.payCost( cost);
                region.updateDeposit(investment);
                return true;
            }else{
                List<Region> neighbours = Utility.getNeighbourRegions(territory,region);
                for (Region neighbour : neighbours){// loop check exist player own neighbour
                    if(isPlayerOwner(player, neighbour)){
                        region.updateOwner(player);
                        player.payCost( cost);
                        region.updateDeposit(investment);
                        return true;
                    }
                }
            }
        }
        player.payCost(1);
        return false;
    }


    public static boolean collect(Player player, long collect){
        Region currentRegion = player.getCurrentRegion();
        if(isPlayerOwner(player,currentRegion)){
            if(player.getBudget() >= 1){
                if(currentRegion.getDeposit() >= collect){
                    currentRegion.updateDeposit(-1 * collect);
                    player.addBudget((long) collect);
                }
                if(currentRegion.getDeposit() <= 0){
                    currentRegion.updateOwner(null);
                }
                if(player.getCityCenter().getDeposit() < 1){
                    player.updateCityCenter(null);
                }
                player.payCost(1);
                return true;
            }
        }
        return false;
    }

    public static boolean shoot(Territory territory, Player player, Direction direction, long expenditure){

        Region attackingRegion = getRegionInDirection(territory, player.getCurrentRegion(), direction);
        long cost = expenditure + 1;
        if(player.getBudget() >= cost){
            if(attackingRegion.getOwner() != null && isPlayerOwner(player, player.getCurrentRegion())){
                attackingRegion.updateDeposit(-1 * expenditure);
                if(attackingRegion.getDeposit() < 1){
                    attackingRegion.updateOwner(null);
                }
                if(player.getCityCenter().getDeposit() < 1){
                    player.updateCityCenter(null);
                }
            }
            player.payCost(cost);
            return true;
        }
        return false;
    }

    private static int[] calculateNewXY(Direction direction, Region current) {
        int x = current.getRegionX();
        int y =  current.getRegionY();
        int parity = y % 2;
        x = x + direction.getDirectionCoordinates(parity)[0];
        y = y + direction.getDirectionCoordinates(parity)[1];
        return new int[]{x,y} ;
    }
}