Feature: Login feature

  @R001
  Scenario: R001 Valid Login
    Given I open Login page
    When I login with valid cred
    Then I see avatar on HomePage

  Scenario Outline: R002 Login with invalid creds
    Given I open Login page
    When I enter '<login>' into input Login in Login page
    And I enter '<password>' into input PassWord in Login page
    And I click on button SignIn in Login page
    Then I see alert message with text 'Invalid username/password.'

    Examples:
      | login           | password           |
      | qaauto          | not_valid_password |
      | not_valid_login | 123456qwerty       |

  Scenario Outline: R003 Validation Registration Messages
    Given I open Login page
    When I enter '<username>' into Registration UserName Field in Login page
    And I enter '<email>' into Registration Email Field in Login page
    And I enter '<password>' into Registration Password Field in Login page
    Then I see '<expectedMessages>' Errors Messages

    Examples:
      | username | email | password     | expectedMessages                                                                                                         |
      | tr       | tr3   | tr3          | Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters. |
      | taras    | tr1   | tr2          | You must provide a valid email address.;Password must be at least 12 characters.                                         |
      | taras    | tr1   | 123456qwerty | You must provide a valid email address.                                                                                  |