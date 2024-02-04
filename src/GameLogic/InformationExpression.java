package GameLogic;

import HexWorld.Player;
import HexWorld.Region;
import HexWorld.Territory;

import static GameLogic.Direction.DIRECTION;

public class InformationExpression {
    private static final Region INVALID_REGION = new Region(-1,-1,true);
    public static int nearby(Territory territory, Player player, int dir) {
        Region opponent;
        opponent = findOpponentRegion(territory, player, dir, Integer.MAX_VALUE);
        return 100 * Region.distance(player.getCurrentAt(), opponent);
    }
    
    public static int opponent(Territory territory, Player player){
        Region region = INVALID_REGION;
        int maxLength = Math.max(
                Math.max(
                        player.getCurrentAt().getCoordinate()[0],
                        player.getCurrentAt().getCoordinate()[1]),
                Math.max(
                        player.getCurrentAt().getCoordinate()[0] - territory.getTerritorySize()[0],
                        player.getCurrentAt().getCoordinate()[1] - territory.getTerritorySize()[1])
                );
        int distance = 1;
        for (int j = 1; j <= maxLength; j++) {
            for (int i = 1; i <= 6; i++) {
                region = findOpponentRegion(territory, player, i, distance);
                if(!region.equals(INVALID_REGION)) {
                    return Integer.parseInt(j + String.valueOf(i));
                }
            }
            distance++;
        }
        return 0;
    }

    private static Region findOpponentRegion(Territory territory, Player player, int dir, int dis){
        if(dir-1 < 0 || dir - 1 > 6){
            System.err.println("Invalid Direction");
            return INVALID_REGION;
        }
        int currentX = player.getCurrentAt().getCoordinate()[0];
        int currentY = player.getCurrentAt().getCoordinate()[1];
        int newX, newY, distance = 0, parity = currentY % 2;;

        Region region;
        Player owner;

        while (!isAtEdge(territory, currentX, currentY) && distance <= dis) {
            newX = currentX + DIRECTION[parity][dir-1][0];
            newY = currentY + DIRECTION[parity][dir-1][1];

            if(isValidRegion(territory, newX, newY)){
                currentX = newX;
                currentY = newY;
                region = territory.getRegion(newX,newY);
                owner = region.owner();
                if(owner != null && !owner.equals(player)){
                    return region;
                }
            }
            distance++;
            parity = (parity + 1) % 2;
        }
        return INVALID_REGION; // not found
    }

    private static boolean isAtEdge(Territory territory, int x, int y) {
        Region[][] grid = territory.getWorld();
        return x <= 0 || x >= grid.length || y <= 0 || y+1 >= grid[0].length;
    }

    private static boolean isValidRegion(Territory territory, int x, int y) {
        Region[][] grid = territory.getWorld();
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }
}
