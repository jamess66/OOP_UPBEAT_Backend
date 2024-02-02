import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final int defaultValue = 0;
    private static final String configFilePath = "Config/config.properties";
    public static Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream config = new FileInputStream(configFilePath)) {
            properties.load(config);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static int getIntProperty(String key) {
        Properties properties = loadConfig();
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ignored){

            }
        }
        return defaultValue;
    }

}
