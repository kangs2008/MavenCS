import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",

        glue = {"chromeSteps"},
        plugin = {"pretty","html:target/cucumber-report.html"},
        tags = "@debug")
public class DemoRunner {
}