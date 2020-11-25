package vn.anibis.util;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.ActionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CapabilityUtil {
    private static final Logger LOGGER = Logger.getLogger(CapabilityUtil.class);


    public static Capabilities loadCapability(String path) {
        ActionType platform = ActionType.valueOf(Configuration.instance().getValue(Configuration.WEB_BROWSER_NAME).toUpperCase());
        return loadCapability(path, platform);
    }

    public static Capabilities loadCapability(String path, ActionType platform) {
        MutableCapabilities cap = null;
        FileReader fileReader = null;
        switch (platform) {
            case IE:
                cap = new InternetExplorerOptions();
                break;
            case IOS:
                cap = DesiredCapabilities.iphone();
                break;
            case EDGE:
                cap = new EdgeOptions();
                break;
            case CHROME:
                cap = new ChromeOptions();

                break;
            case SAFARI:
                cap = new SafariOptions();
                break;
            case ANDROID:
                cap = DesiredCapabilities.android();
                break;
            case FIREFOX:
                cap = new FirefoxOptions();
                break;
            default:
                cap = new DesiredCapabilities();
                break;
        }
        if (path != null && !path.isEmpty() && new File(path).exists()) {
            try {
                fileReader = new FileReader(path);
                cap = new Gson().fromJson(fileReader, cap.getClass());
            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        }
        return cap;
    }
}