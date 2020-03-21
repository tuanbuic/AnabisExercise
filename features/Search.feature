@SearchFeature
Feature: User search functionality
  As a User, I can able to search category and filter condition

  Background:Go to Search Page
    When ANB - I go to "Search" Page
    And ANB - I change language to "de" in "Home" Page
    Then ANB - I should see "Search" Page loaded successfully

  @Searching_Criteria
  Scenario: User can use Searching function with criteria
    When ANB - I search with below filter
      | Category   | category.name     |
      | Location   | location.value    |
      | Distance   | location.distance |
      | Price from | price.from        |
      | Price to   | price.to          |
    Then ANB - I should see "20" item in Page "1"

  @Searching_Sort
  Scenario: User can sort list product by date
    When ANB - I sorting with "date"
    And ANB - I verify newest Product are sorted