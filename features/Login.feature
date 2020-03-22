@LoginFeature
Feature: Users login functionality
  As a user I want to login to the anibis

  Background: Open Anibis Home Page
    Given ANB - I go to "Login" Page
    Then ANB - I should see "Login" Page loaded successfully

  @Login_VerifyCusor
  Scenario: Verify cursor in Login Page
    Then ANB - I should see cursor in username field

  @Login_HappyCase
  Scenario: Verify User can login successfully
    When ANB - I login to system with account "valid.username" and password "valid.password"
    Then ANB - I can login successfully

  @Login_UnHappyCase
  Scenario Outline: Verify User can't login with wrong username
    When ANB - I input username "<username>"
    And ANB - I click on submit button
    Then ANB - I should see messge "<message>" with color "<color>"
    Examples:
      | username         | message                                  | color   |
      |                  | N’oubliez pas cette information          | #D01D00 |
      | @gmail.com       | Indiquez votre adresse e-mail            | #D01D00 |
      | aaaaaa           | Indiquez votre adresse e-mail            | #D01D00 |
      | abc@.com         | Entrez votre adresse e-mail correctement | #D01D00 |
      | a@b@c@anabis.com | Entrez votre adresse e-mail correctement | #D01D00 |
      | .test@domain.com | Entrez votre adresse e-mail correctement | #D01D00 |