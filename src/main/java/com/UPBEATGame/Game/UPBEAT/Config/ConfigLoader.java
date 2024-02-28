package com.UPBEATGame.Game.UPBEAT.Config;


import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final int defaultValue = 0;
    private static final String configFilePath = "src/main/java/com/UPBEATGame/Game/UPBEAT/Config/config.properties";

    @Getter
    public static final  long
            rows,
            cols,
            init_plan_min,
            init_plan_sec,
            init_budget,
            init_center_dep,
            plan_rev_min,
            plan_rev_sec,
            rev_cost,
            max_dep
    ;
    @Getter
    public static final float interest_pct;


    static {
        rows = getLongProperties("m");
        cols = getLongProperties("n");
        init_plan_min = getLongProperties("init_plan_min");
        init_plan_sec = getLongProperties("init_plan_sec");
        init_budget = getLongProperties("init_budget");
        init_center_dep = getLongProperties("init_center_dep");
        plan_rev_min = getLongProperties("plan_rev_min");
        plan_rev_sec = getLongProperties("plan_rev_sec");
        rev_cost = getLongProperties("rev_cost");
        max_dep = getLongProperties("max_dep");
        interest_pct = getFloatProperties("interest_pct");
    }

    public static Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream config = new FileInputStream(configFilePath)) {
            properties.load(config);
        } catch (IOException ex) {
            ex.getMessage();
        }
        return properties;
    }

    public static long getLongProperties(String key) {
        Properties properties = loadConfig();
        if (!properties.containsKey(key)) {
            System.err.println("Error: Property '" + key + "' not found in the configuration file.");
            return defaultValue;
        }
        String value = properties.getProperty(key);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ignored) {
            System.err.println("Error: Invalid value for property '" + key + "'. Default value used.");
            return defaultValue;
        }
    }

    public static float getFloatProperties(String key) {
        Properties properties = loadConfig();
        if (!properties.containsKey(key)) {
            System.err.println("Error: Property '" + key + "' not found in the configuration file.");
            return defaultValue;
        }
        String value = properties.getProperty(key);
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException ignored) {
            System.err.println("Error: Invalid value for property '" + key + "'. Default value used.");
            return defaultValue;
        }
    }
}
