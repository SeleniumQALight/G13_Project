Feature: Validate registration errors

  Scenario Outline: Validate registration errors
    Given I open registration page
    When I type username "<username>"
    And I type email "<email>"
    And I type password "<password>"
    And I click Sign Up button
    Then I should see validation error "<error>"

    Examples:
      | username | email           | password        | error                                      |
      |          |                 |                 | Username must be at least 3 characters.    |
      | user     | invalid         | short           | You must provide a valid email address.    |
      | user     | test@test.com   | 123             | Password must be at least 12 characters.   |