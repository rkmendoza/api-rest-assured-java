@regression
Feature: Create Booking API
  As an API user
  I want to create new bookings
  So that I can reserve rooms

  @create
  @crud
  @positive
  Scenario: Create new booking with valid data
    Given I set base URL for RESTful Booker API
    And I set valid booking data
    When I send POST request to "/booking"
    Then response status code should be 200
    And response should contain booking ID
    And response should contain booking details
    And response time should be less than 5000 milliseconds
    And save booking ID from response

  @create
  @crud
  @positive
  Scenario: Create booking with specific names
    Given I set base URL for RESTful Booker API
    And I set booking data with firstname "Sofia" and lastname "Garcia"
    When I send POST request to "/booking"
    Then response status code should be 200
    And response should contain booking ID
    And response should contain firstname "Sofia"
    And response should contain lastname "Garcia"
    And save booking ID from response