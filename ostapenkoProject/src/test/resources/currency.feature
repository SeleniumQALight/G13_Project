Feature: Validate currency rates between API and UI

  Scenario Outline: R005 Validate currency rates between API and UI
    Given I get currency rate from API for <currency>
    When I open PrivatBank UI and get rate for <currency>
    Then I compare API and UI rates for <currency>

    Examples:
      | currency |
      | USD      |
      | EUR      |