package vn.anibis.core.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface WebAction extends Action {
    void click(String objPath) throws Exception;
    void typeText(String objPath, String text) throws Exception;
    WebElement findElement(String objPath) throws Exception;
    List<WebElement> findElements(String objPath);
    By getBy(String objPath);
    void goToURL(String URL);
    void selectItemByText(String objPath, String text) throws Exception;
    void selectItemByIndex(String objPath, int index) throws Exception;
    void selectItemByValue(String objPath, String value) throws Exception;
    Boolean waitVisibility(String objPath, int timeout);
    Boolean waitExist(String objPath, int timeout);
    void setWaitTimeOut(int time);
    String getPageTitle();
    WebElement getCusor();
    String getCSSValue(String objpath, String cssValueName) throws Exception;
    void initDriver() ;
    void quitDriver();
    void acceptAlert() throws Exception;
    Boolean isAlertPresent() throws Exception;
    String buildObjPathByParams(String objPath, String... params) throws Exception;
    Boolean checkListIsSorted(String objPath, String sortOrder) throws Exception;
    Boolean checkListIsSorted(String objPath, String sortOrder, String attributeName) throws Exception;
    void clearText(String objPath) throws Exception;
    void clearTextWithHotKeys(String objPath) throws Exception;


}
