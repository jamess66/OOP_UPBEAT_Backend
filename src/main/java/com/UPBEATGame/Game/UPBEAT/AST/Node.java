package com.UPBEATGame.Game.UPBEAT.AST;

import com.UPBEATGame.Game.UPBEAT.GameData.Game.PlayerInstance;
import lombok.Getter;

public abstract class Node {
    public abstract static class Expr extends Node {
        public abstract long eval(PlayerInstance playerInstance);
        public abstract String toString();
    }
    @Getter
    public abstract static class Exec extends Node {
        public Exec next;
        public abstract boolean execute(PlayerInstance playerInstance);
    }
}