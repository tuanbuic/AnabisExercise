package vn.anibis.testrunner.hook;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.ActionType;

public class Hooks {
    @Before
    public void BeforeSteps() {
        System.out.println("----------------------------Begin Running Test Scenario----------------------------");
        Configuration.instance().initDriver(ActionType.valueOf(Configuration.instance().getValue(Configuration.WEB_BROWSER_NAME).toUpperCase()));

    }

    @After
    public void AfterSteps() {
        Configuration.driver.quit();
    }
}
