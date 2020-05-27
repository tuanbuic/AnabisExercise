package vn.anibis.test.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import vn.anibis.test.pages.CommonPage;
import vn.anibis.test.pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeSteps {
    @Steps
    CommonPage commonPage;
    @Steps
    HomePage homePage;

    @Then("^ANB - I should see \"([^\"]*)\" Page loaded successfully$")
    public void anbIVerifyAnibisPageLoadedSuccessfully(String page) {
        String expectedResult = null;
        switch (page.toUpperCase()) {
            case "HOME":
                expectedResult = commonPage.getDataValue("anabis.home.title");
                break;
            case "LOGIN":
                expectedResult = commonPage.getDataValue("anabis.login.title");
                break;
            case "SEARCH":
                expectedResult = commonPage.getDataValue("anabis.search.title");
        }
        assertThat(commonPage.getPageTitle()).isEqualTo(expectedResult);
        Serenity.takeScreenshot();
    }

    @When("^ANB - I click on login button$")
    public void anbUserClickOnButton() throws Exception {
        homePage.clickLogin();
    }



}