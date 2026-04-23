Feature: Currency rate comparison

  Scenario Outline: Compare API and UI rates for different currencies
    Given I open PrivatBank home page
    When I get "<currency>" rates from API
    And I get "<currency>" rates from UI
    Then I compare API and UI rates

    Examples:
      | currency |
      | USD      |
      | EUR      |