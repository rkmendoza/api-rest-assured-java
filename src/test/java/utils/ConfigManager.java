package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("No se pudo encontrar config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl(String apiName) {
        return getProperty(apiName);
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("default.timeout"));
    }

    // ✅ MÉTODO QUE FALTABA
    public static String getEnvironment() {
        return getProperty("environment");
    }

    // ✅ MÉTODOS ADICIONALES ÚTILES
    public static String getAuthUsername() {
        return getProperty("auth.username");
    }

    public static String getAuthPassword() {
        return getProperty("auth.password");
    }

    public static int getRetryCount() {
        return Integer.parseInt(getProperty("retry.count"));
    }
}