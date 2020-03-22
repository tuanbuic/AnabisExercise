@End2EndFeature @a
  Feature: User end 2 end flow
    Scenario: User login and search and filter
      Given ANB - I go to "Home" Page
      And ANB - I click on login button
      And ANB - I login to system with account "valid.username" and password "valid.password"
      And ANB - I back to HomePage
      And ANB - I click on search button
      And ANB - I search with below filter
        | Category   | category.name     |
        | Location   | location.value    |
        | Distance   | location.distance |
        | Price from | price.from        |
        | Price to   | price.to          |
      Then ANB - I should see "20" item in Page "1"