Feature: Checkout information
  Scenario: validate Data for checkout information
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
    And Navigate to Cart page
    And Click "checkout" button
    And fill checkout form with the following details
      |FirstName|LastName|postalCode|
      ||Mohotsi|  7140|
    And Click "continue" button
    Then  checkout form shows error "Error: First Name is required"
  Scenario: validate Data for checkout information
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
    And Navigate to Cart page
    And Click "checkout" button
    And fill checkout form with the following details
      |FirstName|LastName|postalCode|
      |Thapelo||  7140|
    And Click "continue" button
    Then  checkout form shows error "Error: Last Name is required"
  Scenario: validate Data for checkout information
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
    And Navigate to Cart page
    And Click "checkout" button
    And fill checkout form with the following details
      |FirstName|LastName|postalCode|
      |Thapelo|Mohotsi|  |
    And Click "continue" button
    Then  checkout form shows error "Error: Postal Code is required"



