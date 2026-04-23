Feature: Login feature

  @R001
  Scenario: R001 Valid login
    Given I open Login page
    When I login with valid credentials
    Then I see avatar on Home page

  Scenario Outline: R002 Invalid login
    Given I open Login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input PassWord in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login         | password           |
      | qaauto        | not_valid_password |
      | invalid_login | 123456qwerty       |

  Scenario Outline: R003 Validation Registration Message
    Given I open Login page
    When I enter '<username>' into Registration Username input in Login page
    And I enter '<email>' into Registration Email input in Login page
    And I enter '<password>' into Registration Password input in Login page
    Then I see '<validationMessage>' text validation message

    Examples:
      | username | email | password     | validationMessage                                                                                                         |
      | tr       | tr1   | tr2          | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | taras    | tr1   | tr2          | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | taras    | tr1   | 123456qwerty | You must provide a valid email address.                                                                                  |