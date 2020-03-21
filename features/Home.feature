@HomeFeature
Feature:User navigation functionality
  As a User, I want to navigate to other page and login successfully

  Background:User go to Home Page
    Given ANB - I go to "Home" Page
    Then ANB - I should see "Home" Page loaded successfully

  @Home_NavigationLogin
  Scenario: Go to Login Page from HomePage
    When ANB - I click on login button
    Then ANB - I should see "Login" Page loaded successfully

  @Home_NavigationLoginWithUser
  Scenario: Go to Login Page and Login successfully
    When ANB - I click on login button
    And ANB - I login to system with account "valid.username" and password "valid.password"
    Then ANB - I can login successfully

  @Home_NavigationSearch
    Scenario: Go to Search Page fromm HomePage
      When ANB - I click on search button
      Then ANB - I should see "Search" Page loaded successfully