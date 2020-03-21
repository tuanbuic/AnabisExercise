package vn.anibis.test.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import vn.anibis.core.config.Configuration;
import vn.anibis.test.pages.CommonPage;
import vn.anibis.test.pages.HomePage;

public class CommonSteps {
    @Steps
    CommonPage commonPage;
    @Steps
    HomePage homePage;

    @Given("^ANB - I go to \"([^\"]*)\" Page$")
    public void anbIGoTo(String page) throws Throwable {
        homePage.goToPage(page);

    }

    @And("^ANB - I change language to \"([^\"]*)\" in \"([^\"]*)\" Page$")
    public void anbIChangeLanguageToInPage(String language, String page) throws Exception {
        commonPage.changeLanguage(language, page);
    }
}
