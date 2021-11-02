package vn.anibis.testrunner.hook;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import vn.anibis.core.config.Configuration;
import vn.anibis.core.enums.ActionType;
import vn.anibis.page.BasePage;
import vn.anibis.test.pages.CommonPage;

public class Hooks {
//    @Before
//    public void BeforeSteps() throws Exception {
//        System.out.println("----------------------------Begin Running Test Scenario----------------------------");
////        Configuration.instance().initDriver(ActionType.valueOf(Configuration.instance().getValue(Configuration.WEB_BROWSER_NAME).toUpperCase()));
//        webAction.initDriver();
//    }
//
//    @After
//    public void AfterSteps() {
////        Configuration.driver.quit();
//        webAction.quitDriver();
//    }
@Before
public void setUp(Scenario scenario) {
    System.out.println("[" + CommonPage.getCurrentDate("dd-MM-YYY hh-mm-ss") + "] *** Start: " + scenario.getName());
}
}
