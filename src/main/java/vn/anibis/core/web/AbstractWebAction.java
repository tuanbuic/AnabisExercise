package vn.anibis.core.web;

import com.google.inject.internal.cglib.proxy.$Dispatcher;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.ActionType;
import vn.anibis.core.repository.ObjectRepository;
import vn.anibis.util.StringUtil;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class AbstractWebAction extends AbstractAction implements WebAction {
    private static final Logger LOGGER = Logger.getLogger(AbstractWebAction.class);
//    public static WebDriver driver;
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private WebElement currentElement;
    int defaultTimeOut = 10;


    public void click(String objPath) throws Exception {
        findElement(objPath);
        currentElement.click();
    }

    @Override
    public void typeText(String objpath, String text) throws Exception {
        findElement(objpath);
        currentElement.clear();
        currentElement.sendKeys(text);

    }

    @Override
    public WebElement findElement(String objPath) throws Exception {
        this.currentElement = null;

        this.currentElement = driver.get().findElement(getBy(objPath));
        if (currentElement == null) {
            LOGGER.info("Cannot find " + objPath);
            throw new Exception("Cannot find " + objPath);
        } else {
            return currentElement;
        }
    }

    @Override
    public List<WebElement> findElements(String objPath) {
        return driver.get().findElements(getBy(objPath));
    }

    @Override
    public By getBy(String objPath) {
        return ObjectRepository.instance().getBy(objPath);
    }

    @Override
    public void openBrowser() {
//        initDriver(ActionType.valueOf(Configuration.instance().getValue("web.browser.name").toUpperCase()));

    }
    @Override
    public void initDriver(){
        String timeout = Configuration.instance().getValue("object.wait.timeout");
            if (timeout != null && !timeout.isEmpty()) {
                defaultTimeOut = Integer.parseInt(timeout);
            }
            driver.get().manage().timeouts().implicitlyWait(defaultTimeOut, TimeUnit.SECONDS);
            driver.get().manage().timeouts().pageLoadTimeout(defaultTimeOut,TimeUnit.SECONDS);
            driver.get().manage().deleteAllCookies();
    }

    @Override
    public void goToURL(String URL) {
        driver.get().manage().window().maximize();
        driver.get().get(URL);
    }

    @Override
    public void selectItemByText(String objPath, String text) throws Exception {
        findElement(objPath);
        Select select = new Select(currentElement);
        select.selectByVisibleText(text);
    }

    @Override
    public void selectItemByIndex(String objPath, int index) throws Exception {
        findElement(objPath);
        Select select = new Select(currentElement);
        select.selectByIndex(index);
    }

    @Override
    public void selectItemByValue(String objPath, String value) throws Exception {
        findElement(objPath);
        Select select = new Select(currentElement);
        select.selectByValue(value);
    }

    @Override
    public Boolean waitVisibility(String objPath, int timeout) {
        boolean r = false;
        try {
            FluentWait<WebDriver> wait = createFluentWait(timeout);
            r = (null != wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(objPath))));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            setWaitTimeOut(defaultTimeOut);
        }
        return r;
    }

    @Override
    public Boolean waitExist(String objPath, int timeout) {
        Boolean r = false;
        try {
            FluentWait<WebDriver> wait = createFluentWait(timeout);
            r = (null != wait.until(ExpectedConditions.presenceOfElementLocated(getBy(objPath))));
        } catch (TimeoutException te) {
            r = false;
        } finally {
            setWaitTimeOut(defaultTimeOut);
        }
        return r;
    }

    @Override
    public void setWaitTimeOut(int time) {
        driver.get().manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    @Override
    public String getPageTitle() {
        return driver.get().getTitle();
    }

    @Override
    public WebElement getCusor() {
        return driver.get().switchTo().activeElement();
    }

    @Override
    public String getCSSValue(String objpath, String cssValueName) throws Exception {
        findElement(objpath);
        return this.currentElement.getCssValue(cssValueName);
    }

    public FluentWait<WebDriver> createFluentWait(int timeout) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver.get())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        setWaitTimeOut(0);
        return wait;
    }
    @Override
    public void quitDriver(){
        driver.get().quit();
    }
}
