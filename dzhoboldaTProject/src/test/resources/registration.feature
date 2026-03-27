Feature: Registration feature

  Scenario Outline: TC001 Check validation messages on registration form
    Given I open Login page
    When I enter '<username>' into 'Username' registration field
    And I enter '<email>' into 'Email' registration field
    And I enter '<password>' into 'Password' registration field
    Then I see error messages '<expectedMessages>'

    Examples:
      | username | email | password      | expectedMessages                                                                                                         |
      | mi       | mi1   | mi2           | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | mio      | mi1   | mi254344      | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | taras    | mi1   | mi2344gffthds | You must provide a valid email address.                                                                                  |