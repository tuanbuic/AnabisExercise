package vn.anibis.core.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WebAction {
    void click(String objPath) throws Exception;
    void typeText(String objPath,String text) throws Exception;
    WebElement findElement(String objPath) throws Exception;
    List<WebElement> findElements(String objPath);
    By getBy(String objPath);
    void openBrowser();
    void goToURL(String URL);
    void selectItemByText(String objPath, String text) throws Exception;
    void selectItemByIndex(String objPath,int index) throws Exception;
    void selectItemByValue(String objPath, String value) throws Exception;
    Boolean waitVisibility(String objPath, int timeout);
    Boolean waitExist(String objPath, int timeout);
    void setWaitTimeOut(int time);
    String getPageTitle();
    WebElement getCusor();
    String getCSSValue(String objpath, String cssValueName) throws Exception;

}
