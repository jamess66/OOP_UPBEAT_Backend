import Config.ConfigLoader;
import HexWorld.Player;
import HexWorld.Region;
import HexWorld.Territory;
import HexWorld.WorldGrid;

import java.util.Arrays;

import static GameLogic.ActionCommand.*;

public class Main {
    static final int test = 5;
    public static void main(String[] args) {
        int initBudget = ConfigLoader.getIntProperty("init_budget");

        System.out.println(initBudget);
        Territory territory = new WorldGrid(10,10);

        Region start = territory.getRegion(5,5);
        Region goal = territory.getRegion(5,9);

        Player player1 = new Player(territory,4,5);
        Player player2 = new Player(territory,5,5);

        territory.getRegion(3,5).claim(player1);
        territory.getRegion(3,5).deposit = 50;
        System.out.println(territory.getRegion(3,5).deposit);
        System.out.println(player1.wallet());
        System.out.println(territory.getRegion(3,5).owner());
        move(territory, player1, "up");
        collect(player1, 50);
        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));
        System.out.println(territory.getRegion(3,5).deposit);
        System.out.println(player1.wallet());
        System.out.println(territory.getRegion(3,5).owner());
//        territory.printClaimTable();
//
//        System.out.println(Arrays.toString(player1.getCityCenter().getCoordinate()));
//        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));
//        System.out.println(player1.wallet());
//        move(territory,player1,"up");
//        move(territory,player1,"up");
//        territory.getRegion(2,5).claim(player1);
//        System.out.println(Arrays.toString(player1.getCityCenter().getCoordinate()));
//        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));
//        System.out.println(player1.wallet());
//        relocate(player1);
//        System.out.println(Arrays.toString(player1.getCityCenter().getCoordinate()));
//        System.out.println(Arrays.toString(player1.getCurrentAt().getCoordinate()));
//        System.out.println(player1.wallet());

    }
}
