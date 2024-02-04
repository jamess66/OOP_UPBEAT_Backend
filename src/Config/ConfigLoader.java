package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final int defaultValue = 0;
    private static final String configFilePath = "Config/config.properties";
    public static final int
            TERRITORY_WEIGH = getIntProperty("territory_weigh"),
            TERRITORY_HEIGHT = getIntProperty("territory_height"),
            INIT_PLAN_MIN = getIntProperty("init_plan_min"),
            INIT_PLAN_SEC = getIntProperty("init_plan_sec"),
            INIT_BUDGET = getIntProperty("init_budget"),
            INIT_CENTER_DEP = getIntProperty("init_center_dep"),
            PLAN_REV_MIN = getIntProperty("plan_rev_min"),
            PLAN_REV_SEC = getIntProperty("plan_rev_sec"),
            REV_COST = getIntProperty("rev_cost"),
            MAX_DEP = getIntProperty("max_dep"),
            INTEREST_PCT = getIntProperty("interest_pct")
    ;


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
