package com.UPBEATGame.Game.UPBEAT.GameData.Game.Commands;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.HexRegion;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameData.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility;
import com.UPBEATGame.Game.UPBEAT.GameData.Utility.Direction;

import java.util.List;

import static com.UPBEATGame.Game.UPBEAT.GameData.Utility.getRegionInDirection;
import static com.UPBEATGame.Game.UPBEAT.GameData.Utility.isPlayerOwner;


public class GameCommands {

    public static boolean done(){
        return false;
    }

    public static boolean relocate(Player player){
        Region currentRegion = player.getCurrentRegion();
        Region currentCenterCity = player.getCityCenter();
        long cost = (5 * Utility.getDistance(currentRegion, currentCenterCity)) + 10;
        if(player.getBudget() >= cost){
            if(currentRegion.getOwner() != null && isPlayerOwner(player,currentRegion)){ // cannot relocate if the region is opponent region but still pay cost.
                player.updateCityCenter(currentRegion);
                player.payCost(cost);
            }
            player.payCost(cost);
        }
        return done(); // Once executed (regardless of the outcome), the evaluation of the construction plan in that turn ends.
    }

    public static boolean move(Territory territory, Player player, Direction direction){
        if(player.getBudget() >= 1){
            Region current = player.getCurrentRegion();
            int[] newXY = calculateNewXY(direction, current);
            if(Utility.isValidCoordinate(newXY[0], newXY[1])){ // do nothing if move to edge of the map
                Region newRegion = territory.getRegion(newXY[0], newXY[1]);
                if(newRegion.getOwner() == null ) player.updateCurrentRegion(newRegion);
                else if(isPlayerOwner(player,newRegion)) player.updateCurrentRegion(newRegion);
            }
            player.payCost(1);
            return true;
        }
        return done();// If the player does not have enough budget to execute this command, the evaluation of the construction plan in that turn ends.
    }

    public static boolean invest(Territory territory , Player player, long investment){
        Region region = player.getCurrentRegion();
        if(player.getBudget() >= 1 && player.getBudget() >= investment){
            if(isPlayerOwner(player,region)) {
                player.payCost(investment);
                region.updateDeposit(investment);
            }else{
                List<Region> neighbours = Utility.getNeighbourRegions(territory,region);
                for (Region neighbour : neighbours){// loop check exist player own neighbour
                    if(isPlayerOwner(player, neighbour)){
                        region.updateOwner(player);
                        player.payCost(investment);
                        region.updateDeposit(investment);
                    }
                }
            }
            player.payCost(1);
        }
        return true; // just no-op not turn end
    }


    public static boolean collect(Player player, long collect){
        Region currentRegion = player.getCurrentRegion();

        if(collect >= 1){
            if(isPlayerOwner(player,currentRegion)){
                if(currentRegion.getDeposit() >= collect){
                    currentRegion.updateDeposit(-1 * collect);
                    player.addBudget(collect);
                }
                if(currentRegion.getDeposit() <= 0){
                    currentRegion.updateOwner(null);

                }
                if(player.getCityCenter().getDeposit() < 1){
                    player.updateCityCenter(null);
                }
            } // not owner assume no-op
            player.payCost(1);
            return true;
        }
        return done(); // If the player does not have enough budget to execute this command, the evaluation of the construction plan in that turn ends
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

            }
            player.payCost(cost);
        }
        return true;
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