Feature: Update Booking API
  As an API user
  I want to update existing bookings
  So that I can modify reservation details

  @update
  Scenario: Update booking with valid data
    Given I set base URL for RESTful Booker API
    And I set valid booking data
    And I send POST request to "/booking"
    And save booking ID from response
    And I set booking data with firstname "Updated" and lastname "Name"
    When I send PUT request to "/booking/{id}" with booking ID
    Then response status code should be 200
    And put response should contain firstname "Updated"
    And put response should contain lastname "Name"