Feature: Posts feature

  @deletePostsTillPresentForDefaultUser
  Scenario Outline: R003 Check number of posts on UI
    Given I create <numberOfPosts> new posts via API for 'default' user and 'default' password
      | title  | <titleEx>    |
      | body   | body of post |
      | select | One Person   |
    And I open Home page as 'default' user and 'default' password
    When I click on button MyProfile on Header Element
    Then I was redirected to MyProfile page
    And I see <numberOfPosts> posts in Posts list on MyProfile Page


    Examples:
      | numberOfPosts | titleEx     |
      | 2             | Post by API |