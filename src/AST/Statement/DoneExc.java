package AST.Statement;

import GameLogics.Engine.PlayerInstance;

import static AST.Node.*;

public class DoneExc extends Exec {
    @Override
    public boolean execute(PlayerInstance game) {
        return true;
    }
}