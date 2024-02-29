package com.UPBEATGame.Game.UPBEAT.GameLogics;

import com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.HexRegion;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader.cols;
import static com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader.rows;

public class Utility {
    public static long getRandomNumber(){
        Random random = new Random();
        return random.nextLong();
    }
    public static long getRandomNumber(long bound){
        Random random = new Random();
        return random.nextLong(0, bound);
    }

    public static long getDistance(Region start, Region end){
        return Math.abs(start.getRegionX() - end.getRegionX()) + Math.abs(start.getRegionY() - end.getRegionY());
    }

    public static Region getRegionInDirection(Territory territory, Region current, Direction direction){
        int parity = current.getRegionY() % 2;
        int[] regionCoordinate = new int[]{current.getRegionX(), current.getRegionY()};
        int[] directionCoordinates = direction.getDirectionCoordinates(parity);
        int[] neighbourCoordinate = new int[]{regionCoordinate[0] + directionCoordinates[0], regionCoordinate[1] + directionCoordinates[1]};
        if(isValidCoordinate(neighbourCoordinate[0], neighbourCoordinate[1])){
            return territory.getRegion(neighbourCoordinate[0], neighbourCoordinate[1]);
        }
        return new HexRegion(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static List<Region> getNeighbourRegions(Territory territory ,Region region){
        List<Region> neighbours = new ArrayList<>();
        //int parity = region.getRegionY() % 2;

        for (Direction direction: Direction.values()){
            Region neighbour = getRegionInDirection(territory, region, direction);
            if(isValidRegion(neighbour)){
                neighbours.add(neighbour);
            }


        }
        return  neighbours;
    }

    public static boolean isPlayerOwner(Player player, Region region){
        return player.equals(region.getOwner());
    }

    public static boolean isValidCoordinate(int x, int y) {
        return x < rows && x >= 0 && y < cols && y >= 0;
    }

    public static boolean isValidRegion(Region region) {
        return region.getRegionX() < rows && region.getRegionX() >= 0 && region.getRegionY() < cols && region.getRegionY() >= 0;
    }

    public static Direction IntegerToDirection(int dir){
        return switch (dir){
            case 1 -> Direction.Up;
            case 2 -> Direction.UpRight;
            case 3 -> Direction.DownRight;
            case 4 -> Direction.Down;
            case 5 -> Direction.DownLeft;
            case 6 -> Direction.UpLeft;
            default -> null;
        };
    }

    public enum Direction {
        Up(new int[]{-1, 0},new int[]{-1, 0}, 1),
        UpRight(new int[]{0, +1}, new int[]{-1, +1}, 2),
        DownRight(new int[]{+1, +1}, new int[]{0, +1}, 3),
        Down(new int[]{+1, 0}, new int[]{+1, 0}, 4),
        DownLeft(new int[]{+1, -1}, new int[]{0, -1}, 5),
        UpLeft(new int[]{0, -1}, new int[]{-1, -1}, 6);

        private final int[] evenRowCoord;
        private final int[] oddRowCoord;
        @Getter
        private final int intDirection;
        Direction(int[] evenRowCoord, int[] oddRowCoord, int intDirection){

            this.evenRowCoord = evenRowCoord;
            this.oddRowCoord = oddRowCoord;
            this.intDirection = intDirection;
        }

        public int[] getDirectionCoordinates(int parity){
            return parity % 2 == 0 ? evenRowCoord: oddRowCoord;
        }

    }

    public static Region getRandomRegion(Territory territory){
        return territory.getRegion((int) getRandomNumber(ConfigLoader.getRows()), (int) getRandomNumber(ConfigLoader.getCols()));
    }
}
