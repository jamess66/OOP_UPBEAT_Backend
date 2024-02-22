package AST.Statement;

import GameLogics.Engine.PlayerInstance;

import static AST.Node.*;

public class RelocateExc extends Exec {
    @Override
    public boolean execute(PlayerInstance command) {
        command.relocate();
        return true;
    }
}