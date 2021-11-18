Feature: Order product Items

  Scenario: Order a Multiple items
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item "Sauce Labs Onesie" to cart
    And Add item "Sauce Labs Fleece Jacket" to cart
    And Navigate to Cart page
    Then Verify that only items which were added to the cart appear
    And Click "checkout" button
    And fill checkout form with the following details
    |FirstName|LastName|postalCode|
    |thapelo Daniel|Mohotsi|7140  |
    And Click "continue" button
    Then Verify that only items which were added to the cart appear










