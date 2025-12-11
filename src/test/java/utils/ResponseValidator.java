package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code validation failed. Response: " + response.getBody().asString());
    }

    public static void validateResponseContains(Response response, String key, Object expectedValue) {
        Object actualValue = response.jsonPath().get(key);
        Assert.assertEquals(actualValue, expectedValue,
                "Field '" + key + "' validation failed. Expected: " + expectedValue + ", Actual: " + actualValue);
    }

    public static void validateResponseNotNull(Response response, String key) {
        Object value = response.jsonPath().get(key);
        Assert.assertNotNull(value, "Field '" + key + "' should not be null");
    }

    public static void validateResponseTime(Response response, long maxTime) {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime <= maxTime,
                "Response time exceeded. Expected: <= " + maxTime + "ms, Actual: " + responseTime + "ms");
    }
}