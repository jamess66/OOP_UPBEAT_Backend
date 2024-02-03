import GameLogic.*;

import java.util.Arrays;
import java.util.Objects;

import static GameLogic.LookUpAction.opponent;

public class Main {
    public static void main(String[] args) {

        Territory territory = new WorldGrid(10,10);

        territory.setRegionBlock(5,4, true);

        Region start = territory.getRegion(5,5);
        Region goal = territory.getRegion(5,9);

        Player player1 = new Player(territory,5,5);
        Player player2 = new Player(territory,5,5);

//        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));
//
//        MoveAction.moveTo(territory, player1, goal);
//
//        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));

        System.out.println(territory.getRegion(0,5).owner());
//
        //territory.getRegion(5,6).claim(player2);
        territory.getRegion(0,5).claim(player2);
        territory.getRegion(0,8).claim(player1);
        territory.getRegion(9,9).claim(player1);

        System.out.println(territory.getRegion(0,5).owner());
        System.out.println(territory.getRegion(0,8).owner());
        System.out.println(territory.getRegion(0,7).owner());

        System.out.println(Arrays.toString(opponent(territory, player1).getCoordinate()));

        territory.printClaimTable();

        //System.out.println(opponent(territory, player1));
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(Arrays.toString(territory.getRegoin(i, j).getCoordinate()));
//            }
//            System.out.println();
//        }
    }
}
