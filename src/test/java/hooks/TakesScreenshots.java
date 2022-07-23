package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

public class TakesScreenshots {


    private WebDriverManager webDriverManager;

//    @After
    public void takesScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) webDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException webDriverException) {
                System.err.println(webDriverException.getMessage());
            } catch (ClassCastException classCastException) {
                classCastException.printStackTrace();
            }
        }
    }
}
