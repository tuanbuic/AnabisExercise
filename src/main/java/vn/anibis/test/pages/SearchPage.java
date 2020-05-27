package vn.anibis.test.pages;

import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.Keys;
import vn.anibis.core.repository.ObjectRepository;

import java.util.Map;

public class SearchPage extends CommonPage {


    public void searchWithCriteria(Map<String, String> dataTable) throws Exception {
        dataTable = transformData(dataTable);
        if (dataTable.get("Category") != null) {
            String xpath = ObjectRepository.instance().getObjectRepository().getProperty("search.category.list");
            click(getXpathCategory(xpath, dataTable.get("Category")));
        }
        if (dataTable.get("Price from") != null) {
            String xpath = ObjectRepository.instance().getObjectRepository().getProperty("search.priceRange.input");
            waitVisibility(xpathIndex(xpath, 1), 10);
            typeText(xpathIndex(xpath, 1), dataTable.get("Price from"));
        }
        if (dataTable.get("Price to") != null) {
            String xpath = ObjectRepository.instance().getObjectRepository().getProperty("search.priceRange.input");
            typeText(xpathIndex(xpath, 2), dataTable.get("Price to"));
        }
        if (dataTable.get("Location") != null) {
            typeText("search.location.input", dataTable.get("Location") + Keys.TAB);
        }
        if (dataTable.get("Distance") != null) {
            webAction.waitExist("search.distance.select", 5);
            webAction.selectItemByValue("search.distance.select", "10");
        }
    }

    private String getXpathCategory(String xpath, String category) {
        return xpath + "[contains(text(),'" + category + "')]";
    }

    public int getListProduct() {
        return webAction.findElements("search.productResult.list").size();
    }

    public void sortingWithCondition(String condition) throws Exception {
        switch (condition.toUpperCase()) {
            case "DATE":
                webAction.selectItemByValue("search.sorting.select", "dpo|d");
                break;
            case "HIGH PRICE":
                webAction.selectItemByValue("search.sorting.select", "pri|d");
                break;
            case "LOW PRICE":
                webAction.selectItemByValue("search.sorting.select", "pri|a");
                break;
            default:
                break;
        }
    }

    public void clickOnSearchButton() throws Exception {
        webAction.click("search.tab.button");
    }
}
