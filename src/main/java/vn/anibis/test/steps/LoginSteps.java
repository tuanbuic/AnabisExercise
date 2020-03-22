package vn.anibis.test.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.eo.Se;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import vn.anibis.test.pages.CommonPage;
import vn.anibis.test.pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginSteps {
    @Steps
    CommonPage commonPage;
    @Steps
    LoginPage loginPage;
    @Then("^ANB - I should see cursor in username field$")
    public void anbIShouldSeeCursorInUsernameField() {
        assertEquals(commonPage.findElement("login.username.textbox"), commonPage.getCursor());
        Serenity.takeScreenshot();
    }

    @When("^ANB - I login to system with account \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void anbILoginToSystemWithAccountAndPassword(String username, String password) throws Exception {
        loginPage.logInWithAccount(username,password);
    }

    @Then("^ANB - I can login successfully$")
    public void anbICanLoginSuccessfully() {
        commonPage.waitVisibility("login.accountName",10);
        assertEquals(commonPage.getDataValue("account.name"),commonPage.findElement("login.accountName").getText());
        Serenity.takeScreenshot();
    }

    @When("^ANB - I input username \"([^\"]*)\"$")
    public void anbIInputUsername(String username) throws Exception {

        loginPage.inputUsername(username);
    }

    @And("^ANB - I click on submit button$")
    public void anbIClickOnSubmitButton() throws Exception {
        loginPage.clickSubmit();
    }

    @Then("^ANB - I should see messge \"([^\"]*)\" with color \"([^\"]*)\"$")
    public void anbIShouldSeeMessgeWithColor(String message, String color) throws Throwable {
        commonPage.waitVisibility("login.error.message",10);
        String actual = commonPage.findElement("login.error.message").getText();
        String actualColor =commonPage.getColor("login.error.message","color").toUpperCase();
        assertEquals("Error message not match",message,actual.trim());
        assertEquals("Color message not match",color,actualColor.trim());
        Serenity.takeScreenshot();

    }
}
