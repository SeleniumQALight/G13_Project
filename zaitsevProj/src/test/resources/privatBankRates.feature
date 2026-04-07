Feature: PrivatBank exchange rates comparison

  @R004
  Scenario Outline: Compare API and UI rates for currency
    Given I get PrivatBank API rate for '<currency>' currency
    When I open PrivatBank home page
    And I save UI rate for current currency from PrivatBank home page
    Then I compare API and UI rates for current currency

    Examples:
      | currency |
      | EUR      |
      | USD      |