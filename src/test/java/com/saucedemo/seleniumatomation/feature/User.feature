Feature: Test website with a user that slow down the system or system delay to respond
  Scenario: Do end to end testing of odering with slow system user performance
    Given User is on "Login Page" page
    When login with username "performance_glitch_user"
    And Add item(s) to cart
      |itemName|
      |Sauce Labs Onesie|
      | Sauce Labs Fleece Jacket|
    And shopping cart badge increments by number of items added to cart
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
  Scenario: sort products name ascending order with slow performance user
    Given User is on "Login Page" page
    When login with username "performance_glitch_user"
    And sort by "Name (A to Z)"
    Then items by "name" in "asc"ending order
  Scenario: sort products by name descending order
    Given User is on "Login Page" page
    When login with username "standard_user"
    And sort by "Name (Z to A)"
    Then items by "name" in "desc"ending order

  Scenario Outline: Login with wrong username and/or password
    Given User is on "Login Page" page
    When login with username "<username>" and password "<password>"
    Then validation message is displayed "<error>"
    Examples:
      |username|password|error|
      |locked_out_user|secret_sauce|Epic sadface: Sorry, this user has been locked out.|
      | thapelo       |secret_sauce |   Epic sadface: Username and password do not match any user in this service  |
      |standard_user  |wrongpassword|Epic sadface: Username and password do not match any user in this service     |
      |wronguser      |wrongpassword|Epic sadface: Username and password do not match any user in this service     |
