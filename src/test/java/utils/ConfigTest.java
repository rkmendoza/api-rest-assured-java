package utils;

public class ConfigTest {
    public static void main(String[] args) {
        System.out.println("=== 🧪 TESTING CONFIG MANAGER ===");

        try {
            System.out.println("Booker URL: " + ConfigManager.getBaseUrl("restful.booker"));
            System.out.println("JSONPlaceholder URL: " + ConfigManager.getBaseUrl("jsonplaceholder"));
            System.out.println("Timeout: " + ConfigManager.getTimeout());
            System.out.println("Environment: " + ConfigManager.getEnvironment());
            System.out.println("=== ✅ CONFIG TEST COMPLETED ===");
        } catch (Exception e) {
            System.out.println("=== ❌ CONFIG TEST FAILED ===");
            e.printStackTrace();
        }
    }
}