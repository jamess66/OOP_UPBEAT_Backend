package com.UPBEATGame.Game.UPBEAT.GameData.Region;

import com.UPBEATGame.Game.UPBEAT.GameData.Player.Crew;
import com.UPBEATGame.Game.UPBEAT.GameData.Player.Player;
import org.junit.Test;

import static org.testng.AssertJUnit.*;

public class HexGridTest {
    @Test
    public void testPlayerLose(){
        Territory testTerri = new HexGrid(20,20);
        Player testPlayer = new Crew(testTerri, "00");

        testTerri.getRegion(1,9).updateOwner(testPlayer);
        testTerri.getRegion(5,1).updateOwner(testPlayer);
        testTerri.getRegion(3,5).updateOwner(testPlayer);
        testTerri.getRegion(4,9).updateOwner(testPlayer);
        testTerri.getRegion(1,6).updateOwner(testPlayer);
        testTerri.getRegion(4,7).updateOwner(testPlayer);

        assertEquals(testPlayer, testTerri.getRegion(1,9).getOwner());
        assertEquals(testPlayer, testTerri.getRegion(5,1).getOwner());
        assertEquals(testPlayer, testTerri.getRegion(3,5).getOwner());
        assertEquals(testPlayer, testTerri.getRegion(4,9).getOwner());
        assertEquals(testPlayer, testTerri.getRegion(1,6).getOwner());
        assertEquals(testPlayer, testTerri.getRegion(4,7).getOwner());

        testTerri.removeLosePlayerRegion(testPlayer);


        assertEquals(null, testTerri.getRegion(1,9).getOwner());
        assertEquals(null, testTerri.getRegion(5,1).getOwner());
        assertEquals(null, testTerri.getRegion(3,5).getOwner());
        assertEquals(null, testTerri.getRegion(4,9).getOwner());
        assertEquals(null, testTerri.getRegion(1,6).getOwner());
        assertEquals(null, testTerri.getRegion(4,7).getOwner());
    }

    @Test
    public void testAllClaimed(){
        Territory testTerri = new HexGrid(20,20);
        Player testPlayer = new Crew(testTerri, "00");

        for (Region[] regions : testTerri.getGrid()) {
            for (Region region : regions) {
                region.updateOwner(testPlayer);
            }
        }


    }
}
