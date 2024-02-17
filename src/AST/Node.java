package AST;

import Models.Commands;

public abstract class Node {
    public abstract static class Expr extends Node {
        public abstract long eval(Commands commands);
        public abstract String toString();
    }

    public abstract static class Exec extends Node {
        public Exec next;
        public abstract boolean execute(Commands commands);
    }
}