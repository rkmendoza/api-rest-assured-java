package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Auth;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    private static String authToken;

    public static RequestSpecification buildRequest() {
        return given()
                .contentType(ContentType.JSON);
                //.accept(ContentType.JSON)
                //.log().all();
    }

    public static RequestSpecification buildRequestWithAuth() {
        // Si no tenemos token, lo generamos
        if (authToken == null) {
            generateAuthToken();
        }

        return buildRequest()
                .contentType(ContentType.JSON)
                //.accept(ContentType.JSON)
                .cookie("token", authToken);
    }

    private static void generateAuthToken() {
        Auth authCredentials = Auth.builder()
                .username("admin")
                .password("password123")
                .build();

        Response response = buildRequest()
                .body(authCredentials)
                .post(ConfigManager.getBaseUrl("restful.booker") + "/auth");

        authToken = response.jsonPath().getString("token");

        if (authToken == null) {
            throw new RuntimeException("No se pudo obtener el token de autenticación");
        }
    }

    public static Response get(String endpoint) {
        return buildRequest().get(endpoint);
    }

    public static Response post(String endpoint, Object body) {
        return buildRequest().body(body).post(endpoint);
    }

    public static Response put(String endpoint, Object body) {
        return buildRequestWithAuth().body(body).put(endpoint);
    }

    public static Response patch(String endpoint, Object body) {
        return buildRequestWithAuth().body(body).patch(endpoint);
    }

    public static Response delete(String endpoint) {
        return buildRequestWithAuth().delete(endpoint);
    }

    // Método para limpiar el token (útil para tests de auth)
    public static void clearAuthToken() {
        authToken = null;
    }
}