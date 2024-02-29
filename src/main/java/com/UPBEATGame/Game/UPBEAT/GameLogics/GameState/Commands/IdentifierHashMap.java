package com.UPBEATGame.Game.UPBEAT.GameLogics.GameState.Commands;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class IdentifierHashMap extends HashMap<String, Long> {
    @Override
    public Long get(Object key) {
        if (containsKey(key)) {
            return super.get(key);
        } else {
            return 0L;
        }
    }
}