Feature: Remove And Add Items and test

  Scenario: Order a Multiple items and remove in Shopping page
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
      |Sauce Labs Fleece Jacket|
      |Sauce Labs Bolt T-Shirt |
    And shopping cart badge increments by number of items added to cart
    And Remove Item from Cart
      |itemName|
      |Sauce Labs Fleece Jacket|
    And Navigate to Cart page
    Then Verify that only items which were added to the cart appear


    And Click "checkout" button
    And fill checkout form with the following details
      |FirstName|LastName|postalCode|
      |thapelo Daniel|Mohotsi|7140  |
    And Click "continue" button
    Then Verify that only items which were added to the cart appear
    Then Verify the total Amount charge is correct
    And Click "Finish" button
    Then Confirmation message should displayed with
      |header|complete|title|
      |THANK YOU FOR YOUR ORDER|Your order has been dispatched, and will arrive just as fast as the pony can get there!|CHECKOUT: COMPLETE!|

  Scenario: Order a Multiple items and remove in Cart page
    Given User is on "Login Page" page
    When login with username "standard_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
      |Sauce Labs Fleece Jacket|
      |Sauce Labs Bolt T-Shirt |
    And shopping cart badge increments by number of items added to cart

    And Navigate to Cart page
    And Remove Item from Cart
      |itemName|
      |Sauce Labs Fleece Jacket|
    Then Verify that only items which were added to the cart appear


    And Click "checkout" button
    And fill checkout form with the following details
      |FirstName|LastName|postalCode|
      |thapelo Daniel|Mohotsi|7140  |
    And Click "continue" button
    Then Verify that only items which were added to the cart appear
    Then Verify the total Amount charge is correct
    And Click "Finish" button
    Then Confirmation message should displayed with
      |header|complete|title|
      |THANK YOU FOR YOUR ORDER|Your order has been dispatched, and will arrive just as fast as the pony can get there!|CHECKOUT: COMPLETE!|





