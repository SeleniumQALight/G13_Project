Feature: Currency rates comparison

  Scenario Outline: Compare API and UI rates for <currency>
    Given I get rates for "<currency>" from API
    When I open home page
    And I get rates for "<currency>" from UI
    Then API and UI rates should match

    Examples:
      | currency |
      | USD      |
      | EUR      |