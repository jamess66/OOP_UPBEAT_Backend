package com.UPBEATGame.Game.UPBEAT.Config;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final int defaultValue = 0;
    private static final String configFilePath = "src/main/java/com/UPBEATGame/Game/UPBEAT/Config/config.properties";

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

    public static long getRows() {
        return rows;
    }
    public static long getCols() {
        return cols;
    }
    public static long getInit_budget() {
        return init_budget;
    }
    public static long getRev_cost(){
        return rev_cost;
    }
    public static long getMax_dep() {
        return max_dep;
    }
    public static float getInterest_pct() {
        return interest_pct;
    }
    public static long getInit_center_dep() {
        return init_center_dep;
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
