Feature: Login feature

  Scenario Outline: Compare PB exchange rates obtained via API and via UI
    Given I get PB "<currency>" rates via API
    And I open PB Home page
    When I see "<currency>" rates on the Home page
    Then I compare "<currency>" rates obtained via API and via UI

    Examples:
      | currency |
      | USD      |
      | EUR      |