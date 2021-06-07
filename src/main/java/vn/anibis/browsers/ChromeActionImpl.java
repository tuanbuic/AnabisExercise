package vn.anibis.browsers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.web.AbstractWebAction;
import vn.anibis.util.CapabilityUtil;

public class ChromeActionImpl extends AbstractWebAction {
    @Override
    public void initDriver() throws  Exception {
        System.setProperty("webdriver.chrome.driver", Configuration.instance().getValue(Configuration.WEB_DRIVER_PATH));
        ChromeOptions options = (ChromeOptions) CapabilityUtil.loadCapability(Configuration.instance().getValue(Configuration.WEB_CAPABILITIES_FILES));
        driver.set(new ChromeDriver(options));
        super.initDriver();
    }
}
