package vn.anibis.test.pages;

import org.apache.log4j.Logger;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.web.AbstractWebAction;
import vn.anibis.page.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonPage extends BasePage {
    static final Logger logger = Logger.getLogger(CommonPage.class.getName());
//    AbstractWebAction abstractWebAction = new AbstractWebAction();

    public void stepException(String mes) {
        throw new RuntimeException(mes);
    }

    public void stepException(Exception e) {
        throw new RuntimeException(e);
    }

    public void gotoURL(String URL) {
        try {
            webAction.goToURL(URL);
        } catch (TimeoutException e) {
            Assert.fail("Timeout because of Page loading too long");
        }
    }

    public void click(String path, int sec) throws Exception {
        try {
            webAction.waitVisibility(path, sec);
            webAction.click(path);
        } catch (Exception e) {
            stepException(e);
        }
    }

    public void click(String path) throws Exception {
        click(path, 10);
    }

    public void typeText(String path, String text, int sec) {
        try {
            webAction.waitVisibility(path, sec);
            webAction.typeText(path, text);
        } catch (Exception e) {
            stepException(e);
        }
    }
    public List<WebElement> findElements(String objPath){
       return webAction.findElements(objPath);
    }

    public void typeText(String path, String text) {
        typeText(path, text, 10);
    }

    public WebElement findElement(String path) {
        try {

            return webAction.findElement(path);
        } catch (Exception e) {
            stepException(e);
        }
        return null;
    }

    public String getDataValue(String key) {
        return Configuration.instance().getDataValue(key);
    }

    public String getPageTitle() {

        return webAction.getPageTitle();
    }

    public WebElement getCursor() {
        return webAction.getCusor();
    }

    public String getColor(String objpath, String cssValueName) throws Exception {
        return Color.fromString(webAction.getCSSValue(objpath, cssValueName)).asHex();
    }

    public Map<String, String> transformData(Map<String, String> dataMap) {
        Map<String, String> newDataMap = new HashMap<>();
        for (String key : dataMap.keySet()) {
            String newValue = dataMap.get(key);
            if (System.getProperty(key) != null) {
                newValue = System.getProperty(key);
            } else if (getDataValue(newValue) != null) {
                newValue = getDataValue(newValue);
            }
            newDataMap.put(key, newValue);
        }
        return newDataMap;
    }

    public void changeLanguage(String language, String page) throws Exception {
        if (page.equalsIgnoreCase("Home")) {
            switch (language.toUpperCase()) {
                case "DE":
                    webAction.selectItemByValue("language.select", "0");
                    break;
                case "FR":
                    webAction.selectItemByValue("language.select", "1");
                    break;
                case "IT":
                    webAction.selectItemByValue("language.select", "2");
                    break;
                default:
                    break;
            }
        }
        if (page.equalsIgnoreCase("Login")) {
            switch (language.toUpperCase()) {
                case "DE":
                    click("login.language.de");
                    break;
                case "FR":
                    click("login.language.fr");
                    break;
                case "IT":
                    click("login.language.it");
                    break;
                default:
                    break;
            }
        }
    }

    public String xpathIndex(String xpath, int index) {
        if (index <= 0)
            return xpath;
        return "(" + xpath + ")" + "[" + index + "]";
    }

    public boolean waitVisibility(String xpath, int sec) {
        try {
            return webAction.waitVisibility(xpath, sec);
        } catch (Exception e) {
            return false;
        }
    }
}