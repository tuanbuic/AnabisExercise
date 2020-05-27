import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;


/**
 * Unit test for simple App.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(glue = {"vn.anibis.test.steps", "vn.anibis.testrunner.hook"}, features = "features", tags = {"@Home_NavigationLoginWithUser"}, plugin = {"pretty"})
public class RunnerTest {
}
