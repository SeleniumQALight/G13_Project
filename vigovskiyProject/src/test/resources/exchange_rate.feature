Feature: PrivatBank exchange rates comparison (API vs UI)

  Scenario Outline: Compare exchange rate from API and UI

    Given I get exchange rate for "<currency>" from API
    When I get exchange rate for "<currency>" from UI
    Then I compare API rate with UI rate for "<currency>"

    Examples:
      | currency |
      | USD      |
      | EUR      |