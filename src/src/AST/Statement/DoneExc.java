package src.AST.Statement;

import Models.Command;

import static AST.Node.*;

public class DoneExc extends Exec {
    @Override
    public boolean execute(Command game) {
        System.out.println("Perform DoneExc");
        return true;
    }
}