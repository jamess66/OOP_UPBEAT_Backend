package GameLogics.Engine;

import GameLogics.Region.Territory;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameCommandTest {
    GameUPBEAT gameState = GameState.getGameInstance();

    Territory testWorld = gameState.getTerritory();

    PlayerInstance player1 = gameState.createPlayerInstance("jeff", 5,5);

    @Test
    public void testGetGameInstance(){
        assertEquals(gameState, GameState.getGameInstance());
    }

    @Test
    public void testGetPlayerInstance(){
        PlayerInstance player1 = gameState.createPlayerInstance("james");
        PlayerInstance player2 = gameState.createPlayerInstance("john");

        assertEquals(player1, GameState.getGameInstance().getPlayerInstance("james"));
        assertEquals(player2, GameState.getGameInstance().getPlayerInstance("john"));
    }


    @Test
    public void testMoveExec() {
        Assertions.assertEquals(testWorld.getRegion(5,5), player1.getCurrentRegion());
        player1.newConstructionPlan("move up");
        player1.actionExecute();
        Assertions.assertEquals(testWorld.getRegion(4,5), player1.getCurrentRegion());
        player1.newConstructionPlan("move up \n move up move up move up \n move up");
        player1.actionExecute();
        Assertions.assertEquals(testWorld.getRegion(0,5), player1.getCurrentRegion());
    }

    @Test
    public void testAssignExec() {
        Map<String, Long> p1Iden = player1.getIdentifiers();

        System.out.println(p1Iden.get("y")); // if not assign always return 0
        player1.newConstructionPlan("x = random");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x + 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x - 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x * 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x / 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x ^ 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = rows");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = cols");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = deposit");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = budget");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = currow");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = curcol");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));


        player1.newConstructionPlan("x = int");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));
    }

    @Test
    public void testIfExec() {
        player1.newConstructionPlan("if(1000000) x = 555555 - 55555555");
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("x"));

        player1.newConstructionPlan("if(0) x = 000");
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("x"));

        player1.newConstructionPlan("if(1) x = 000");
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("x"));
    }

    @Test
    public void testWhileloopExec() {
        player1.newConstructionPlan("x = 10001 y = 0 \n while ( 1 ) { y = y + 1 x = x - 1 }"); // similar to for(int i = 0; i < 10000: i++) y++;
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("y"));
        System.out.println(player1.getIdentifiers().get("x"));

    }

}
