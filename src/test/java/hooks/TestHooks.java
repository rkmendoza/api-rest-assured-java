package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import utils.ConfigManager;

public class TestHooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("=== Iniciando Scenario: " + scenario.getName() + " ===");

        // Configurar timeout global de RestAssured
//        RestAssured.config = RestAssured.config()
//                .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
//                        .setParam("http.connection.timeout", ConfigManager.getTimeout())
//                        .setParam("http.socket.timeout", ConfigManager.getTimeout()));
        RestAssured.useRelaxedHTTPSValidation();
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=== Finalizando Scenario: " + scenario.getName() +
                " - Status: " + scenario.getStatus() + " ===");
    }
}