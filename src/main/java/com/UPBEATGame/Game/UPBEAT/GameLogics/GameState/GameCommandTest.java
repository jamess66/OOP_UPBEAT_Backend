package com.UPBEATGame.Game.UPBEAT.GameLogics.GameState;

import com.UPBEATGame.Game.UPBEAT.GameLogics.Region.Territory;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GameCommandTest {
    GameUPBEAT gameState = GameDataInstance.getGameInstance();

    Territory testWorld = gameState.getTerritory();

    PlayerInstance player1 = gameState.createPlayerInstance("jeff", 5,5, "55");

    @Test
    public void testGetGameInstance(){
        assertEquals(gameState, GameDataInstance.getGameInstance());
    }

    @Test
    public void testGetPlayerInstance(){
        PlayerInstance player1 = gameState.createPlayerInstance("james",1, "55");
        PlayerInstance player2 = gameState.createPlayerInstance("john",2, "66");

        assertEquals(player1, GameDataInstance.getGameInstance().getPlayerInstance("james"));
        assertEquals(player2, GameDataInstance.getGameInstance().getPlayerInstance("john"));
    }


    @Test
    public void testMoveExec() {
        assertEquals(testWorld.getRegion(5,5), player1.getCurrentRegion());
        player1.newConstructionPlan("move up");
        player1.actionExecute();
        assertEquals(testWorld.getRegion(4,5), player1.getCurrentRegion());
        player1.newConstructionPlan("move up \n move up move up move up \n move up");
        player1.actionExecute();
        assertEquals(testWorld.getRegion(0,5), player1.getCurrentRegion());
    }

    @Test
    public void testAssignExec() {
        Map<String, Long> p1Iden = player1.getIdentifiers();

        player1.newConstructionPlan("x = x + 1 ");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        System.out.println(p1Iden.get("y")); // if not assign always return 0
        player1.newConstructionPlan("x = random");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("t = 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("t"));

        player1.newConstructionPlan("x = x + 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x - 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("x = x * 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("t = t / 5");
        player1.actionExecute();
        System.out.println(p1Iden.get("x"));

        player1.newConstructionPlan("t = t ^ 5");
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

        player1.newConstructionPlan("x = deposit");
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("x"));

        player1.newConstructionPlan("while (1) moved = moved + 1 ");
        System.out.println(player1.getIdentifiers().get("moved"));

    }

    @Test
    public void testSimpleExec() {
        player1.newConstructionPlan(
                """
                            t = t + 1 
                            m = 0  # number of random moves
                        while (deposit) { # still our region
                                if (deposit - 100)
                                    then collect (deposit / 4)  # collect 1/4 of available deposit
                          else if (budget - 25) then invest 25
                          else {}
                                if (budget - 100) then {} else done  # too poor to do anything else
                                opponentLoc = opponent
                                if (opponentLoc / 10 - 1)
                                    then  # opponent afar
                                if (opponentLoc % 10 - 5) then move downleft
                            else if (opponentLoc % 10 - 4) then move down
                            else if (opponentLoc % 10 - 3) then move downright
                            else if (opponentLoc % 10 - 2) then move up
                            else if (opponentLoc % 10 - 1) then move upright
                            else move up
                          else if (opponentLoc)
                                    then  # opponent adjacent to city crew
                                if (opponentLoc % 10 - 5) then {
                                    cost = 10 ^ (nearby upleft % 100 + 1)
                                    if (budget - cost) then shoot upleft cost else {}
                                }
                            else if (opponentLoc % 10 - 4) then {
                                    cost = 10 ^ (nearby downleft % 100 + 1)
                                    if (budget - cost) then shoot downleft cost else {}
                                }
                            else if (opponentLoc % 10 - 3) then {
                                    cost = 10 ^ (nearby down % 100 + 1)
                                    if (budget - cost) then shoot down cost else {}
                                }
                            else if (opponentLoc % 10 - 2) then {
                                    cost = 10 ^ (nearby downright % 100 + 1)
                                    if (budget - cost) then shoot downright cost else {}
                                }
                            else if (opponentLoc % 10 - 1) then {
                                    cost = 10 ^ (nearby upright % 100 + 1)
                                    if (budget - cost) then shoot upright cost else {}
                                }
                            else {
                                    cost = 10 ^ (nearby up % 100 + 1)
                                    if (budget - cost) then shoot up cost else {}
                                }
                          else {  # no visible opponent; move in a random direction
                                        dir = random % 6
                                    if (dir - 4) then move upleft
                            else if (dir - 3) then move downleft
                            else if (dir - 2) then move down
                            else if (dir - 1) then move downright
                            else if (dir) then move upright
                            else move up
                                    m = m + 1
                                }
                            }"""); // similar to for(int i = 0; i < 10000: i++) y++;
        player1.actionExecute();
        System.out.println(player1.getIdentifiers().get("deposit"));
        System.out.println(player1.getIdentifiers().get("budget"));
    }

}
