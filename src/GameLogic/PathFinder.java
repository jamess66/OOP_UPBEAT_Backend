package GameLogic;

import java.util.*;

public class PathFinder {

    private final static int[][][] neighborCoord = new int[][][]{
            {{+1, 0}, {+1, -1}, {0, -1}, {-1, 0}, {0, +1}, {+1, +1}},
            {{+1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, +1}, {0, +1}},
    };

    public static class Point {
        public Point parent;
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static List<Point> findShortestPath(int[][] grid, Point start, Point goal) {
        Set<Point> visited = new HashSet<>();
        List<Point> path = new ArrayList<>();
        Stack<Point> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            Point current = stack.pop();

            if (current.equals(goal)) {
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            visited.add(current);

            Point next = null;
            double minDistance = Integer.MAX_VALUE;
            List<Point> neighbors = getNeighbors(grid, current);

            for (Point neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    double distance = findDistance(neighbor, goal);
                    if (distance < minDistance) {
                        minDistance = distance;
                        next = neighbor;
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

        return null; // No path found
    }

    private static double findDistance(Point start, Point end){
        return Math.sqrt(((end.x - start.x) * (end.x - start.x)) + ((end.y - start.y) * (end.y - start.y)));
    }
    private static List<Point> getNeighbors(int[][] grid, Point current) {
//        int[][][] neighborCoord = new int[][][]{
//                {{+1, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, +1}, {0, +1}},
//                {{+1, 0}, {+1, -1}, {0, -1}, {-1, 0}, {0, +1}, {+1, +1}}
//        };
//
//        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
//        List<Point> neighbors = new ArrayList<>();
//
//        for (int[] dir : directions) {
//            int newX = current.x + dir[0];
//            int newY = current.y + dir[1];
//            if (isValidMove(grid, newX, newY)) {
//                neighbors.add(new Point(newX, newY));
//            }
//        }
//
//        return neighbors;

        List<Point> neighbour = new ArrayList<>();
        int parity = current.y % 2;
        System.out.println(parity +" " +Arrays.deepToString(neighborCoord[parity]));
        for (int[] coord : neighborCoord[parity]) {
            int newX = current.x + coord[0];
            int newY = current.y + coord[1];
            if (isValidMove(grid, newX, newY)){
                System.out.println(current.x + coord[0] + " " + (current.x + coord[1]));
                neighbour.add(new Point(newX, newY));
            }

        }
        return neighbour;
    }

    private static boolean isValidMove(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0;
    }
}
