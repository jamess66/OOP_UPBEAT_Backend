package com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.Commands;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Engine.GameState;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.PlayerInstance;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Crew;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Player.Player;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Region;
import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Utility;
import org.junit.Test;

import static com.UPBEATGame.Game.UPBEAT.Config.ConfigLoader.*;
import static com.UPBEATGame.Game.UPBEAT.GameLogics.Utility.IntegerToDirection;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {



    Territory testWorld = GameState.getGameInstance().getTerritory();

    PlayerInstance player1 = GameState.getGameInstance().createPlayerInstance("james", 5, 5, "55");


    PlayerInstance player2 = GameState.getGameInstance().createPlayerInstance("james", 5, 5, "66");

    @Test
    public void testNearby(){
        Player testPlayer = new Crew(testWorld, "00");
        assertEquals(0, player2.nearby(Utility.Direction.Up));

        testWorld.getRegion(1,5).updateOwner(testPlayer);

        assertEquals(400, player2.nearby(Utility.Direction.Up));
    }

    @Test
    public void testOpponent(){
        Player testPlayer = new Crew(testWorld, "00");
        assertEquals(0, player1.opponent()); // not found

        testWorld.getRegion(1,5).updateOwner(testPlayer);
        assertEquals(41, player1.opponent());

        testWorld.getRegion(5,6).updateOwner(testPlayer);
        assertEquals(13, player1.opponent());
    }

    @Test
    public void testRelocate() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCityCenter());

        assertEquals(init_budget ,player1.getBudget());
        assertTrue(player1.move(Utility.Direction.UpLeft));
        assertTrue(player1.invest(10));
        assertTrue(player1.move(Utility.Direction.UpLeft));
        assertTrue(player1.invest(10));
        assertTrue(player1.move(Utility.Direction.Up));

        assertEquals(init_budget - 11 - 11 - 3 ,player1.getBudget());

        assertEquals(testWorld.getRegion(3, 3), player1.getCurrentRegion());

        assertFalse(player1.relocate());

        // distance 5,5 to 3,3 is 4. (5 * 4) + 10 = 30
        assertEquals(init_budget - 11 - 11 - 3 - 30 ,player1.getBudget());

        assertTrue(player1.invest(10));

        assertTrue(player1.relocate());

        assertEquals(testWorld.getRegion(3, 3), player1.getCityCenter());

    }

    @Test
    public void testMoveUp() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(4, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(3, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(2, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(1, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(0, 5), player1.getCurrentRegion());// on the top of map
        assertTrue(player1.move(Utility.Direction.Up));
        assertEquals(testWorld.getRegion(0, 5), player1.getCurrentRegion());
    }

    @Test
    public void testMoveUpRight() {
        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(4, 6), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(4, 7), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 8), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(3, 9), player1.getCurrentRegion());// on the edge of map
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(2, 10), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.UpRight));
        assertEquals(testWorld.getRegion(2, 11), player1.getCurrentRegion());
    }

    @Test
    public void testMoveToEnemyRegion(){
        Player testPlayer = new Crew(testWorld, "00");
        testWorld.getRegion(7, 5).updateOwner(testPlayer);

        assertEquals(testWorld.getRegion(5, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Down));
        assertEquals(testWorld.getRegion(6, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Down));
        assertEquals(testWorld.getRegion(6, 5), player1.getCurrentRegion());
        assertTrue(player1.move(Utility.Direction.Down));
        assertEquals(testWorld.getRegion(6, 5), player1.getCurrentRegion());
    }

    @Test
    public void testInvest(){
        assertEquals(getInit_center_dep(), player1.getCurrentRegion().getDeposit());
        assertTrue(player1.invest(50));
        assertEquals(getInit_center_dep() + 50, player1.getCurrentRegion().getDeposit());
        assertEquals(init_budget - 51, player1.getBudget());

        player1.move(Utility.Direction.Up); // move to 4,5
        assertNull(player1.getCurrentRegion().getOwner()); // current region not claim yet
        assertEquals(0, player1.getCurrentRegion().getDeposit());
        assertTrue(player1.invest(20)); // claim the region
        assertEquals(20, player1.getCurrentRegion().getDeposit());

        assertTrue(player1.invest(20)); // add 20 deposit to the region
        assertEquals(40, player1.getCurrentRegion().getDeposit());

        player1.move(Utility.Direction.Down); // move to 5,5
        player1.move(Utility.Direction.Down); // move to 6,5
        player1.move(Utility.Direction.Down); // move to 7,5
        player1.move(Utility.Direction.Down); // move to 8,5
        assertNull(player1.getCurrentRegion().getOwner());
        assertEquals(0, player1.getCurrentRegion().getDeposit());
        assertFalse(player1.invest(20));// invest with no player owner neighbour
        assertNull(player1.getCurrentRegion().getOwner());
        assertEquals(0, player1.getCurrentRegion().getDeposit());
    }

    @Test
    public void testShoot(){
        Player testPlayer = new Crew(testWorld, "00");
        testWorld.getRegion(6,5).updateOwner(testPlayer);
        testWorld.getRegion(6,5).updateDeposit(true,20);
        assertEquals(20, testWorld.getRegion(6,5).getDeposit());
        assertEquals(testPlayer, testWorld.getRegion(6,5).getOwner());
        // assume enemy

        assertTrue(player1.attack(Utility.Direction.Down, 10));//cost 11
        assertEquals(9989, player1.getBudget());
        assertEquals(10, testWorld.getRegion(6,5).getDeposit());
        assertEquals(testPlayer, testWorld.getRegion(6,5).getOwner());

        assertTrue(player1.attack(Utility.Direction.Down, 15));// cost 16
        assertEquals(9989-16, player1.getBudget());
        assertEquals(0, testWorld.getRegion(6,5).getDeposit());
        assertNull(testWorld.getRegion(6,5).getOwner());// test player lose region

        // attack null owner
        assertTrue(player1.attack(Utility.Direction.Up, 20));
        assertEquals(9989-16-21, player1.getBudget());// still pay cost


        player1.move(Utility.Direction.Up); // go to 4,5
        player1.invest(20);
        player1.move(Utility.Direction.Up); // go to 3,5

        assertEquals(testWorld.getRegion(3, 5), player1.getCurrentRegion());
        player1.invest(20);

        assertNotNull(testWorld.getRegion(3, 5).getOwner()); // player claimed 3,5

        player1.move(Utility.Direction.Down); // go to 4,5

        assertTrue(player1.attack(Utility.Direction.Up, 200)); // attack player own region

        assertEquals(9989-16-21-201-2-21-21-1, player1.getBudget());// still pay cost and

        assertNull(testWorld.getRegion(3, 5).getOwner()); // player1 lose region by attacking themselves

    }

    @Test
    public void testCollect(){
        assertTrue(player1.collect(10)); // collect from city center
        assertEquals(90, player1.getCurrentRegion().getDeposit());
        assertEquals(10009, player1.getBudget());

        player1.move(Utility.Direction.Up); // move to 4,5
        assertEquals(10009-1, player1.getBudget());
        assertFalse(player1.collect(10)); // collect from unclaimed region

        player1.invest(10); // claim 4,5
        assertEquals(10009- 1 - 11, player1.getBudget());

        assertEquals(10, testWorld.getRegion(4,5).getDeposit());

        assertTrue(player1.collect(20)); // collect 20 but the region have only 10 no-op

        assertNotNull(testWorld.getRegion(4,5).getOwner()); // still own region

        assertTrue(player1.collect(10)); // collect all
        assertNull(testWorld.getRegion(4,5).getOwner()); // lose region
        assertEquals(0, testWorld.getRegion(4,5).getDeposit());

        assertEquals(10009 - 1 - 11 - 1 + 9, player1.getBudget());
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
