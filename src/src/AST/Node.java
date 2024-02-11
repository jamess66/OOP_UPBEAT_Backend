package src.AST;

import Models.Command;

public abstract class Node {
    public abstract static class Expr extends Node {
        public abstract long eval(Command command);
        public abstract String toString();
    }

    public abstract static class Exec extends Node {
        public Exec next;
        public abstract boolean execute(Command command);
    }
}