package vn.anibis.testrunner.hook.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.web.AbstractAction;
import vn.anibis.core.web.AbstractWebAction;

public class ChromeActionImpl extends AbstractWebAction {
    @Override
    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", Configuration.instance().getValue(Configuration.WEB_DRIVER_PATH));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("disable-web-security");
        options.addArguments("-dev-shm-usage");
        driver.set(new ChromeDriver(options));
        super.initDriver();
    }

}
