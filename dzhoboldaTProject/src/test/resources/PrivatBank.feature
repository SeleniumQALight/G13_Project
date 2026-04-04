Feature: PrivatBank Currency Exchange Comparison

  Scenario Outline: Compare exchange rates between API and UI
    Given I request exchange rates from PrivatBank API for "<currency>"
    When I open PrivatBank main page and extract rates for "<currency>"
    Then I compare API rates with UI rates for "<currency>"

    Examples:
      | currency |
      | EUR      |
      | USD      |