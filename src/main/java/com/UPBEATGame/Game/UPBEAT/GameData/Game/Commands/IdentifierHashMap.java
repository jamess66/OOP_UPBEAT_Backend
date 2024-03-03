package com.UPBEATGame.Game.UPBEAT.GameData.Game.Commands;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class IdentifierHashMap extends HashMap<String, Long> {
    @Override
    public Long get(Object key) {
        if (containsKey(key)) {
            return super.get(key);
        } else {
            return 0L; // if not initialize yet just return default value
        }
    }
}