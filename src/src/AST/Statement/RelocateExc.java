package src.AST.Statement;

import Models.Command;

import static AST.Node.*;

public class RelocateExc extends Exec {
    @Override
    public boolean execute(Command gamecmd) {
        System.out.println("Perform RelocateExc");
        gamecmd.relocate();
        return true;
    }
}