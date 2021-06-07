package vn.anibis.core.web;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import vn.anibis.core.AbstractAction;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.repository.ObjectRepository;

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
    public void initDriver() throws Exception {
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
        driver.get().get(URL);
        driver.get().manage().window().maximize();

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

    @Override
    public void acceptAlert() throws Exception {
        if (isAlertPresent()) {
            driver.get().switchTo().alert().accept();
        }
    }


    @Override
    public Boolean isAlertPresent() throws Exception {
        try{
            final WebDriverWait wait = new WebDriverWait(driver.get(),10);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        }catch (final WebDriverException ex){
            if(ex.getMessage().contains("An attempt was made to operate on a modal dialog when one was not open")){
                return false;
            }else{
                throw ex;
            }
        }
    }

    @Override
    public String buildObjPathByParams(String objPath, String... params) throws Exception {
        return null;
    }

    @Override
    public Boolean checkListIsSorted(String objPath, String sortOrder) throws Exception {
        return null;
    }

    @Override
    public Boolean checkListIsSorted(String objPath, String sortOrder, String attributeName) throws Exception {
        return null;
    }

    @Override
    public void clearText(String objPath) throws Exception {

    }

    @Override
    public void clearTextWithHotKeys(String objPath) throws Exception {

    }
    @Override
    public WebDriver getDriver() throws Exception{
        return driver.get();
    }
    @Override
    public void closeDriver()throws Exception{
      if(driver.get()!=null)
          try{
              driver.get().quit();
          }catch(Exception e){}
      driver.remove();
    }


}
