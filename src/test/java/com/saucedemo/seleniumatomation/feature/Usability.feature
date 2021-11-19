Feature: Usability
  Scenario:Each Item Image is distinct
    Given User is on "Login Page" page
    When login with username "standard_user"
    Then validate product items "images are distinct"
