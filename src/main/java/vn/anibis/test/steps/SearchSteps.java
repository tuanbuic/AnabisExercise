package vn.anibis.test.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import vn.anibis.test.pages.CommonPage;
import vn.anibis.test.pages.SearchPage;
import vn.anibis.util.DateUtil;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {
    @Steps
    SearchPage searchPage;
    @Steps
    CommonPage commonPage;

    @When("^ANB - I search with below filter$")
    public void anbISearchWithBelowFilter(Map<String, String> dataTable) throws Exception {
        searchPage.searchWithCriteria(dataTable);
    }

    @Then("^ANB - I should see \"([^\"]*)\" item in Page \"([^\"]*)\"$")
    public void anbIShouldSeeItemInPage(String number, String pageNumber) throws Throwable {
        String actualPageNumber = commonPage.findElement("search.pageNumber.current").getText();
        String actualpProductCount = String.valueOf(searchPage.getListProduct());
        assertThat(actualpProductCount).isEqualTo(number);
        assertThat(actualPageNumber).isEqualTo(pageNumber);
        Serenity.takeScreenshot();
    }

    @When("^ANB - I sorting with \"([^\"]*)\"$")
    public void anbISortingWith(String condition) throws Throwable {
        searchPage.sortingWithCondition(condition);
    }

    @And("^ANB - I verify newest Product are sorted$")
    public void anbIVerifyNewestProductAreSorted() throws Exception {
        int previousDate = 0;
        List<WebElement> elementList = commonPage.findElements("search.productResult.list");
        for (WebElement e : elementList) {
            String[] list = e.getText().split("·");
            String expectedDate = DateUtil.convertDate(list[2].trim(), "yyyyMMdd");
            int latestDate = Integer.parseInt(expectedDate);
            Assert.assertTrue(latestDate >= previousDate);
            previousDate = latestDate;
        }
        Serenity.takeScreenshot();
    }

    @When("^ANB - I click on search button$")
    public void anbIClickOnSearchButton() throws Exception {
        searchPage.clickOnSearchButton();
    }
}
