package com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.Commands;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.HexRegion;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.*;

public class InformationExpression {

    private static final Region INVALID_REGION = new HexRegion();

    public static long nearby(Territory territory, Player player, Direction direction) {
        for (int i = 0; i <= getMaxOperateTime(player.getCurrentRegion()); i++) {
            Region opponent = findOpponentRegion(territory, player, direction, i);
            if(!opponent.equals(INVALID_REGION)){
                return 100 * Utility.getDistance(player.getCurrentRegion(), opponent);
            }
        }
        return 0;
    }

    public static int opponent(Territory territory, Player player){
        Region region;

        for (int i = 1; i <= getMaxOperateTime(player.getCurrentRegion()); i++) {
            for (Direction direction: Direction.values()){
                region = findOpponentRegion(territory, player, direction, i);
                if(!region.equals(INVALID_REGION)){
                    System.out.println("asd");
                    return Integer.parseInt(String.valueOf(i) + direction.getIntDirection());
                }
            }
        }
        return 0;
    }



    private static Region findOpponentRegion(Territory territory, Player player, Direction direction, long distance){

        int currentX = player.getCurrentRegion().getRegionX();
        int currentY = player.getCurrentRegion().getRegionY();
        int newX = 0, newY = 0, parity = currentY % 2;;

        Region region;
        Player owner;

        if(!isAtEdge(currentX, currentY)) {
            newX = currentX;
            newY = currentY;

            for (int i = 0; i < distance; i++) {
                if (isAtEdge(newX, newY)) break;
                newX = newX + direction.getDirectionCoordinates(parity)[0];
                newY = newY + direction.getDirectionCoordinates(parity)[1];
                parity = (parity + 1) % 2;
            }

            if(isValidRegion(newX, newY)){
                region = territory.getRegion(newX,newY);
                owner = region.getOwner();
                if(owner != null && !player.equals(owner)){
                    return region;
                }
            }
        }
        return INVALID_REGION;
    }

    private static boolean isAtEdge(int x, int y) {

        return x <= 0 || x + 1 >= ConfigLoader.rows || y <= 0 || y + 1 >= ConfigLoader.cols;
    }

    private static boolean isValidRegion(int x, int y) {
        return x >= 0 && x < ConfigLoader.rows && y >= 0 && y < ConfigLoader.cols;
    }

    private static long getMaxOperateTime(Region currentRegion){
        return Math.max(
                Math.max(currentRegion.getRegionX(), currentRegion.getRegionY()),
                Math.max(currentRegion.getRegionX() - ConfigLoader.rows, currentRegion.getRegionY() - ConfigLoader.cols));
    }
}
