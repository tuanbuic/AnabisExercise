package vn.anibis.testrunner.hook.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.web.AbstractWebAction;
import vn.anibis.util.CapabilityUtil;

public class FirefoxActionImpl extends AbstractWebAction {
    @Override
    public void initDriver(){
        System.setProperty("webdriver.gecko.driver", Configuration.instance().getValue(Configuration.WEB_DRIVER_PATH));
        FirefoxOptions options = (FirefoxOptions) CapabilityUtil.loadCapability(Configuration.instance().getValue(Configuration.WEB_CAPABILITIES_FILES));
        driver.set(new FirefoxDriver(options));
        super.initDriver();
    }
}
