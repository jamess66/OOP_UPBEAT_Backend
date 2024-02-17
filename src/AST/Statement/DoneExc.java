package AST.Statement;

import Models.Commands;

import static AST.Node.*;

public class DoneExc extends Exec {
    @Override
    public boolean execute(Commands game) {
        System.out.println("Perform DoneExc");
        return true;
    }
}