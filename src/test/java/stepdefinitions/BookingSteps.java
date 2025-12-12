package stepdefinitions;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Booking;
import models.BookingResponse;
import utils.ConfigManager;
import utils.RequestBuilder;
import utils.ResponseValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Properties;

import static org.testng.Assert.*;

public class BookingSteps {

    private Response response;
    private Booking booking;
    private Booking bookingupdate;
    private BookingResponse bookingResponse;
    private int bookingId;
    private String baseUrl = ConfigManager.getBaseUrl("restful.booker");
    private ObjectMapper objectMapper = new ObjectMapper();


    @Given("I set base URL for RESTful Booker API")
    public void setBaseUrl() {
        // Base URL ya está configurada en RequestBuilder
        System.out.println("console log para pruebas de action");
    }


    @Given("I set valid booking data")
    public void setValidBookingData() {
        booking = Booking.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(Booking.BookingDates.builder()
                        .checkin("2024-01-01")
                        .checkout("2024-01-02")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }

    @Given("I set booking data with firstname {string} and lastname {string} to {string}")
    public void setBookingDataWithNames(String firstname, String lastname, String endpoint) {
        booking = Booking.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(Booking.BookingDates.builder()
                        .checkin("2024-02-01")
                        .checkout("2024-02-05")
                        .build())
                .additionalneeds("Lunch")
                .build();

        String finalEndpoint = endpoint.replace("{id}", String.valueOf(bookingId));
        response = RequestBuilder.put(baseUrl + finalEndpoint, booking);

        if (response.getStatusCode() == 200) {
            bookingupdate = response.as(Booking.class);
        }
    }

    @When("I send POST request to {string}")
    public void sendPostRequest(String endpoint) {
        response = RequestBuilder.post(baseUrl + endpoint, booking);

        if (response.getStatusCode() == 200) {
            bookingResponse = response.as(BookingResponse.class);
            System.out.println("✅ BookingResponse del GET guardado");
        }

    }

    @When("I send GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RequestBuilder.get(baseUrl + endpoint);
    }

    @When("I send GET request to {string} with booking ID")
    public void sendGetRequestWithBookingId(String endpoint) {
        String finalEndpoint = endpoint.replace("{id}", String.valueOf(bookingId));
        response = RequestBuilder.get(baseUrl + finalEndpoint);
    }

    @When("I send PUT request to {string} with booking ID")
    public void sendPutRequestWithBookingId(String endpoint) {
        String finalEndpoint = endpoint.replace("{id}", String.valueOf(bookingId));
        response = RequestBuilder.put(baseUrl + finalEndpoint, booking); // ✅ USA AUTH

        if (response.getStatusCode() == 200) {
            bookingupdate = response.as(Booking.class);
        }
    }

    @When("I send DELETE request to {string} with booking ID")
    public void sendDeleteRequestWithBookingId(String endpoint) {
        String finalEndpoint = endpoint.replace("{id}", String.valueOf(bookingId));
        response = RequestBuilder.delete(baseUrl + finalEndpoint); // ✅ USA AUTH
    }

    @Then("response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "status code no es 200, es ---> " + response.getStatusCode());
    }

    @Then("response should contain booking ID")
    public void verifyBookingId() {
        bookingId = response.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0, "Booking ID should be positive");
    }


    @Then("response should contain booking details")
    public void verifyBookingDetails() {
        // ✅ USAR BookingResponse guardado para comparaciones
        assertNotNull(bookingResponse, "BookingResponse no debería ser null");
        assertNotNull(bookingResponse.getBooking(), "Booking no debería ser null");

        assertEquals(bookingResponse.getBooking().getFirstname(), booking.getFirstname(),
                "Firstname no coincide");
        assertEquals(bookingResponse.getBooking().getLastname(), booking.getLastname(),
                "Lastname no coincide");
        assertEquals(bookingResponse.getBooking().getTotalprice(), booking.getTotalprice(),
                "Totalprice no coincide");

        System.out.println("✅ Validación con BookingResponse exitosa");
    }

    @Then("response should contain firstname {string}")
    public void verifyFirstname(String expectedFirstname) {
        assertEquals(bookingResponse.getBooking().getFirstname(), expectedFirstname, "Firstname no coincide");
    }

    @Then("response should contain lastname {string}")
    public void verifyLastname(String expectedLastname) {
        assertEquals(bookingResponse.getBooking().getLastname(), expectedLastname, "Firstname no coincide");

    }

    @Then("response time should be less than {long} milliseconds")
    public void verifyResponseTime(long maxTime) {
        ResponseValidator.validateResponseTime(response, maxTime);
    }

    @Then("save booking ID from response")
    public void saveBookingId() {
        bookingId = response.jsonPath().getInt("bookingid");
    }


    @And("I set booking data with firstname {string} and lastname {string}")
    public void iSetBookingDataWithFirstnameAndLastname(String firstname, String lastname) {
        booking = Booking.builder()
                .firstname(firstname)
                .lastname(lastname)
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(Booking.BookingDates.builder()
                        .checkin("2024-02-01")
                        .checkout("2024-02-05")
                        .build())
                .additionalneeds("Lunch")
                .build();
    }

    @And("put response should contain lastname {string}")
    public void putResponseShouldContainLastname(String expectedLastname) {
        assertEquals(bookingupdate.getLastname(), expectedLastname, "Lastname no coincide");
    }

    @And("put response should contain firstname {string}")
    public void putResponseShouldContainFirstname(String expectedFirsname) {
        assertEquals(bookingupdate.getFirstname(), expectedFirsname, "Firstname no coincide");
    }
}