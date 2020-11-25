package vn.anibis.core.config;

import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import vn.anibis.core.enums.ActionType;
import vn.anibis.util.PropertiesUtil;

import java.io.*;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class Configuration {
//    public static void main(String[] args) throws IOException {
//
//        Configuration.instance().loadConfiguration("config/env.properties");
//        System.out.println(Configuration.instance().getDataValue("c"));
//    }

    public static final String WEB_DRIVER_PATH = "web.driver.path";
    public static final String WEB_BROWSER_NAME = "web.browser.name";
    public static final String OBJ_REPO_FOLDER = "obj.repo.folder";
    public static final  String WEB_CAPABILITIES_FILES="web.capabilities.file";
    public static final String DEFAULT_CONFIG = "config/environment.properties";
    public static final String DEFAULT_CONFIG_FOLDER = "config";
    public static final String DEFAULT_CONFIG_FILE = "config.properties";
    public static final String DEFAULT_DATA_FOLDER = "data";
    public static final String DEFAULT_DATA_FILE = "data.properties";
    static final Logger LOGGER = Logger.getLogger(Configuration.class);
    private static Configuration config;
    Properties props;
    Properties dataProps;
    File dataFolder;
    //initialize webdriver
//    @Managed
//    public static WebDriver driver;

    static int defaultTimeOut = 10;

    public Configuration() {
        props = new Properties();
        dataProps = new Properties();
        dataFolder = new File(DEFAULT_DATA_FOLDER);
        loadConfiguration(DEFAULT_CONFIG);

    }

    public static Configuration instance() {
        if (config == null) {
            config = new Configuration();
        }
        return config;
    }

    public void loadConfiguration(String configFile) {
        try {
            File f = new File(configFile);
            if (f.exists() && !f.isDirectory()) {
                this.props.putAll(PropertiesUtil.load(configFile));
            }
            this.props.putAll(System.getProperties());
            this.loadData();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void loadData() {
        try {
            File dataPropertiesFile = new File(this.dataFolder, "data.properties");
            if (dataPropertiesFile.exists() && !dataPropertiesFile.isDirectory()) {
                this.dataProps.putAll(PropertiesUtil.load(dataPropertiesFile.getAbsolutePath()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public String getValue(String key) {
        return this.props.getProperty(key);
    }

    public String getDataValue(String key) {
        return this.dataProps.getProperty(key);
    }


//    public void initDriver(ActionType platform) {
//        try {
//            switch (platform) {
//                case CHROME:
//                    System.setProperty("webdriver.chrome.driver", getValue(Configuration.WEB_DRIVER_PATH));
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("disable-infobars");
//                    options.addArguments("disable-web-security");
//                    options.addArguments("-dev-shm-usage");
//                    driver = new ChromeDriver(options);
//                    break;
//                case FIREFOX:
//                    System.setProperty("webdriver.gecko.driver", Configuration.instance().getValue(Configuration.WEB_DRIVER_PATH));
//                    driver = new FirefoxDriver();
//                    break;
//            }
//            String timeout = getValue("object.wait.timeout");
//            if (timeout != null && !timeout.isEmpty()) {
//                defaultTimeOut = Integer.parseInt(timeout);
//            }
//            driver.manage().timeouts().implicitlyWait(defaultTimeOut, TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(defaultTimeOut,TimeUnit.SECONDS);
//            driver.manage().deleteAllCookies();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }
}
