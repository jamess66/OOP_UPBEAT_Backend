package GameLogic;

import java.util.*;

public class PathFinder {

    private final static int[][][] neighborCoordinate = new int[][][]{
            {{+1, 0}, {+1, -1}, {0, -1}, {-1, 0}, {0, +1}, {+1, +1}}, // even row
            {{+1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, +1}, {0, +1}}, // odd row
    };

    private final static List<Region> EMPTY_LIST = new ArrayList<>();

    public static List<Region> findShortestPath(Territory world, Region start, Region goal) {
        Set<Region> visited = new HashSet<>();
        List<Region> path = new ArrayList<>();
        Stack<Region> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            Region current = stack.pop();

            if (current.equals(goal)) {
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            visited.add(current);

            Region next = null;
            double minDistance = Integer.MAX_VALUE;
            List<Region> neighbors = getNeighbors(world, current);

            for (Region neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    double distance = getDistance(neighbor, goal);
                    if (distance < minDistance) {
                        minDistance = distance;
                        next = neighbor;
                    }else if(distance == minDistance){
                        assert next != null;
                        if(neighbor.x - goal.x < next.x - goal.x) next = neighbor;
                    }
                }
            }

            if (next != null) {
                stack.push(next);
                next.parent = current;
            } else {
                if (!stack.isEmpty()) {
                    stack.push(stack.peek().parent);
                }
            }
        }

        return EMPTY_LIST; // No path found
    }

    private static double getDistance(Region start, Region end){
        return Math.sqrt(((end.x - start.x) * (end.x - start.x)) + ((end.y - start.y) * (end.y - start.y)));
    }
    protected static List<Region> getNeighbors(Territory world, Region current) {
        Region[][] grid = world.getWorld();
        List<Region> neighbour = new ArrayList<>();
        int parity = current.y % 2;
        for (int[] coordinate  : neighborCoordinate[parity]) {
            int newX = current.x + coordinate[0];
            int newY = current.y + coordinate[1];
            if (isValidRegion(world, newX, newY)){
                neighbour.add(grid[newX][newY]);
            }
        }
        return neighbour;
    }

    private static boolean isValidRegion(Territory world, int x, int y) {
        Region[][] grid = world.getWorld();
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !grid[x][y].isBlocked();
    }
}
