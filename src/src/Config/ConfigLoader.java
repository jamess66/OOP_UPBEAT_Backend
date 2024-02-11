package src.Config;


import Models.Region.Region;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ConfigLoader {
    private static final int defaultValue = 0;
    private static final String configFilePath = "src/Config/config.properties";

    public static final  long
            rows,
            cols,
            init_plan_min,
            init_plan_sec,
            init_budget,
            init_center_dep,
            plan_rev_min,
            plan_rev_sec,
            rev_Cost,
            max_dep,
            interest_pct
    ;

    static {
        rows = getIntProperty("m");
        cols = getIntProperty("n");
        init_plan_min = getIntProperty("init_plan_min");
        init_plan_sec = getIntProperty("init_plan_sec");
        init_budget = getIntProperty("init_budget");
        init_center_dep = getIntProperty("init_center_dep");
        plan_rev_min = getIntProperty("plan_rev_min");
        plan_rev_sec = getIntProperty("plan_rev_sec");
        rev_Cost = getIntProperty("rev_cost");
        max_dep = getIntProperty("max_dep");
        interest_pct = getIntProperty("interest_pct");
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
    public static long getRev_Cost(){
        return rev_Cost;
    }
    public static long getMax_dep() {
        return max_dep;
    }
    public static long getInterest_pct() {
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

    public static int getIntProperty(String key) {
        Properties properties = loadConfig();
        if (!properties.containsKey(key)) {
            System.err.println("Error: Property '" + key + "' not found in the configuration file.");
            return defaultValue;
        }
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
            System.err.println("Error: Invalid value for property '" + key + "'. Default value used.");
            return defaultValue;
        }
    }
}
