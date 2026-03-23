Feature: Login feature

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