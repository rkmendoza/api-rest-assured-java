@regression
Feature: Delete Booking API
  As an API user
  I want to delete bookings
  So that I can cancel reservations

  @delete
  @auth
  @crud
  @positive
  Scenario: Delete existing booking
    Given I set base URL for RESTful Booker API
    And I set valid booking data
    And I send POST request to "/booking"
    And save booking ID from response
    When I send DELETE request to "/booking/{id}" with booking ID
    Then response status code should be 201

  @delete
  @auth
  @crud
  @negative
  Scenario: Verify booking is deleted
    Given I set base URL for RESTful Booker API
    And I set valid booking data
    And I send POST request to "/booking"
    And save booking ID from response
    And I send DELETE request to "/booking/{id}" with booking ID
    When I send GET request to "/booking/{id}" with booking ID
    Then response status code should be 404