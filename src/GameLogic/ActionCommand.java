package GameLogic;

import HexWorld.Player;
import HexWorld.Region;
import HexWorld.Territory;

import static GameLogic.Direction.DIRECTION;

public class ActionCommand {

    static void done(){

    }

    public static void relocate(Player player){
        Region currentRegion = player.getCurrentAt();
        Region currentCenterCity = player.getCityCenter();
        float cost = (5 * Region.distance(currentRegion, currentCenterCity)) + 10;

        if(player.wallet() >= cost){
            if(currentRegion.owner() != null && isPlayerOwner(player,currentRegion)){
                player.moveCityCenter(currentRegion);
                player.pay(cost);
            }
        }
    }

    public static void move(Territory territory, Player player, String direction){
        if(player.wallet() >= 1){
            Region current = player.getCurrentAt();
            int[] newXY = getNewXY(direction, current);
            Region newRegion = territory.getRegion(newXY[0], newXY[1]);
            if(newRegion.owner() == null ){
                player.setCurrentRegion(newRegion);
            }else if(isPlayerOwner(player,newRegion)){
                player.setCurrentRegion(newRegion);
            }
            player.pay(1);
        }

    }


    public static void invest(Player player, float investment){
        Region region = player.getCurrentAt();
        float cost = investment + 1;
        if(player.wallet() >= cost){
            if(region.owner() == null){
                region.claim(player);
                region.invest(player, investment);
            }else if(isPlayerOwner(player,region)){
                region.invest(player, investment);
            }
        }
        player.pay(1);
    }

    public static void collect(Player player, float collect){
        Region currentRegion = player.getCurrentAt();
        if(isPlayerOwner(player,currentRegion)){
            if(player.wallet() >= 1){
                if(currentRegion.getDeposit() >= collect){
                    currentRegion.collect(collect);
                    player.addBudget(collect);
                }
                if(currentRegion.getDeposit() <= 0){
                    currentRegion.unClaim(player);
                }
                player.pay(1);
            }else done();
        }
    }

    public static void shoot(){

    }

    private static int[] getNewXY(String direction, Region current) {
        int[] xyCoord = current.getCoordinate();
        int parity = xyCoord[1] % 2;
        xyCoord = switch (direction) {
            case "up" -> calculateNewXY(xyCoord, DIRECTION[parity][0]);
            case "upright" -> calculateNewXY(xyCoord, DIRECTION[parity][1]);
            case "downright" -> calculateNewXY(xyCoord, DIRECTION[parity][2]);
            case "down" -> calculateNewXY(xyCoord, DIRECTION[parity][3]);
            case "downleft" -> calculateNewXY(xyCoord, DIRECTION[parity][4]);
            case "upleft" -> calculateNewXY(xyCoord, DIRECTION[parity][5]);
            default -> xyCoord;
        };
        return xyCoord;
    }

    private static int[] calculateNewXY(int[] xy, int[] dirXY){
        return new int[]{xy[0] + dirXY[0],xy[1] + dirXY[1]};
    }

    private static boolean isPlayerOwner(Player player, Region region){
        return player.equals(region.owner());
    }
}
