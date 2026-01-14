@smoke
@regression
Feature: Get Booking API
  As an API user
  I want to retrieve booking details
  So that I can view reservation information

  @get-id
  @crud
  @positive
  Scenario: Get booking by ID
    Given I set base URL for RESTful Booker API
    And I set valid booking data
    And I send POST request to "/booking"
    And save booking ID from response
    When I send GET request to "/booking/{id}" with booking ID
    Then response status code should be 200
    And response should contain booking details
    And response match whit the schema

  @get-all
  @crud
  @positive
  Scenario: Get all bookings
    Given I set base URL for RESTful Booker API
    When I send GET request to "/booking"
    Then response status code should be 200