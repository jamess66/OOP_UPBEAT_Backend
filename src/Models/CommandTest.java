package Models;

import Models.Player.Crew;
import Models.Player.Player;
import Models.Region.HexGrid;
import Models.Region.Region;
import Models.Region.Territory;

import org.junit.Test;

import static Config.ConfigLoader.*;
import static Models.Engine.GameCommands.*;
import static Models.Engine.InformationExpression.*;
import static Models.Utility.IntegerToDirection;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    Territory testWorld = new HexGrid();

    Player player1 = new Crew(testWorld, 5, 5, "james");


    Player player2 = new Crew(testWorld, 3, 4, "john");

    @Test
    public void testNearby(){
        assertEquals(0, nearby(testWorld,player1, 1));

        testWorld.getRegion(1,5).updateOwner(player2);

        assertEquals(400, nearby(testWorld,player1, 1));
    }

    @Test
    public void testOpponent(){
        assertEquals(0, opponent(testWorld,player1)); // not found

        testWorld.getRegion(1,5).updateOwner(player2);
        assertEquals(41, opponent(testWorld,player1));

        testWorld.getRegion(5,6).updateOwner(player2);
        assertEquals(13, opponent(testWorld,player1));
    }
    @Test
    public void testRelocate() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCityCenter());
        assertEquals(init_budget ,player1.getBudget());

        player1.updateCurrentRegion(testWorld.getRegion(3, 3));// temporary

        assertFalse(relocate(player1));

        // distance 5,5 to 3,3 is 4. (5 * 4) + 10 = 30
        assertEquals(init_budget - 30 ,player1.getBudget());

        testWorld.getRegion(3, 3).updateOwner(player1);// temporary

        assertTrue(relocate(player1));

        assertEquals(testWorld.getRegion(3, 3), player1.getCityCenter());
        assertEquals(player1, testWorld.getRegion(5, 5).getOwner());

    }

    @Test
    public void testMoveUp() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(4, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(3, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(2, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(1, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(0, 5), player1.getCurrentRegion());// on the top of map
        assertTrue(move(testWorld, player1, Utility.Direction.Up));
        assertEquals(testWorld.getRegion(0, 5), player1.getCurrentRegion());
    }

    @Test
    public void testMoveUpRight() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(4, 6), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(4, 7), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 8), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 9), player1.getCurrentRegion());// on the edge of map
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 9), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 9), player1.getCurrentRegion());
    }

    @Test
    public void testMoveToEnemyRegion(){
        testWorld.getRegion(7, 5).updateOwner(player2);

        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Down));
        assertEquals(testWorld.getRegion(6, 5), player1.getCurrentRegion());
        assertTrue(move(testWorld, player1, Utility.Direction.Down));
        assertEquals(testWorld.getRegion(6, 5), player1.getCurrentRegion());
    }

    @Test
    public void testInvest(){
        assertEquals(getInit_center_dep(), player1.getCurrentRegion().getDeposit());
        assertTrue(invest(testWorld, player1, 50));
        assertEquals(getInit_center_dep() + 50, player1.getCurrentRegion().getDeposit());
        assertEquals(init_budget - 51, player1.getBudget());

        move(testWorld, player1, Utility.Direction.Up); // move to 4,5
        assertNull(player1.getCurrentRegion().getOwner());
        assertEquals(0, player1.getCurrentRegion().getDeposit());
        assertTrue(invest(testWorld, player1, 20));
        assertEquals(player1, player1.getCurrentRegion().getOwner());
        assertEquals(20, player1.getCurrentRegion().getDeposit());

        move(testWorld, player1, Utility.Direction.Down); // move to 5,5
        move(testWorld, player1, Utility.Direction.Down); // move to 6,5
        move(testWorld, player1, Utility.Direction.Down); // move to 7,5
        move(testWorld, player1, Utility.Direction.Down); // move to 8,5
        assertNull(player1.getCurrentRegion().getOwner());
        assertEquals(0, player1.getCurrentRegion().getDeposit());
        assertFalse(invest(testWorld, player1, 20));// invest with no player owner neighbour
        assertNull(player1.getCurrentRegion().getOwner());
        assertEquals(0, player1.getCurrentRegion().getDeposit());
    }

    @Test
    public void testShoot(){
        testWorld.getRegion(6,5).updateOwner(player2);
        testWorld.getRegion(6,5).updateDeposit(true,20);
        assertEquals(20, testWorld.getRegion(6,5).getDeposit());
        assertEquals(player2, testWorld.getRegion(6,5).getOwner());

        assertTrue(shoot(testWorld, player1, Utility.Direction.Down, 10));//cost 11
        assertEquals(9989, player1.getBudget());
        assertEquals(10, testWorld.getRegion(6,5).getDeposit());
        assertEquals(player2, testWorld.getRegion(6,5).getOwner());

        assertTrue(shoot(testWorld, player1, Utility.Direction.Down, 15));// cost 16
        assertEquals(9989-16, player1.getBudget());
        assertEquals(0, testWorld.getRegion(6,5).getDeposit());
        assertNull(testWorld.getRegion(6,5).getOwner());

        // attack null owner
        assertTrue(shoot(testWorld, player1, Utility.Direction.Up, 20));
        assertEquals(9989-16-21, player1.getBudget());// still pay cost


        move(testWorld, player1, Utility.Direction.Up);
        move(testWorld, player1, Utility.Direction.Up);
        testWorld.getRegion(3,5).updateOwner(player2);
        testWorld.getRegion(3,5).updateDeposit(true,20);

        assertTrue(shoot(testWorld, player1, Utility.Direction.Up, 200));
        assertEquals(9989-16-21-201-2, player1.getBudget());// still pay cost but nothing happen

        assertEquals(player2, testWorld.getRegion(3,5).getOwner());
        assertEquals(20, testWorld.getRegion(3,5).getDeposit());
    }

    @Test
    public void testCollect(){
        assertTrue(collect(player1, 10));
        assertEquals(90, player1.getCurrentRegion().getDeposit());
        assertEquals(10009, player1.getBudget());

        move(testWorld, player1, Utility.Direction.Up);

    }
    @Test
    public void testUtility(){
        for(Region region: Utility.getNeighbourRegions(testWorld, testWorld.getRegion(0,0))){
            System.out.println(region.getRegionX() + " " + region.getRegionY());
        }

        assertEquals(Utility.Direction.Up, IntegerToDirection(1));
        assertEquals(Utility.Direction.UpRight, IntegerToDirection(2));
        assertEquals(Utility.Direction.DownRight, IntegerToDirection(3));
        assertEquals(Utility.Direction.Down, IntegerToDirection(4));
        assertEquals(Utility.Direction.DownLeft, IntegerToDirection(5));
        assertEquals(Utility.Direction.UpLeft, IntegerToDirection(6));
        assertNull(IntegerToDirection(7));
    }

}
