Feature: Sort product Items

  Scenario: sort products name ascending order
    Given User is on "Login Page" page
    When login with username "standard_user"
    And sort by "Name (A to Z)"
    Then items by "name" in "asc"ending order
  Scenario: sort products by name descending order
    Given User is on "Login Page" page
    When login with username "standard_user"
    And sort by "Name (Z to A)"
    Then items by "name" in "desc"ending order

  Scenario: sort products price ascending order
    Given User is on "Login Page" page
    When login with username "standard_user"
    And sort by "Price (low to high)"
    Then items by "price" in "asc"ending order
  Scenario: sort products price descending order
    Given User is on "Login Page" page
    When login with username "standard_user"
    And sort by "Price (high to low)"
    Then items by "price" in "desc"ending order

