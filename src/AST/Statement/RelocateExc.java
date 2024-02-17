package AST.Statement;

import Models.Commands;

import static AST.Node.*;

public class RelocateExc extends Exec {
    @Override
    public boolean execute(Commands command) {
        System.out.println("Perform RelocateExc");
        command.relocate();
        return true;
    }
}