package chromeSteps;

import chromePages.chromePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lib.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SimpleReport;

import static com.codeborne.selenide.Selenide.page;

import io.cucumber.java.Scenario;
import io.cucumber.java.*;

@Slf4j
public class chromeSteps extends BasePage {
    //private static final Logger log = LoggerFactory.getLogger(chromeSteps.class);
    private chromePage cpage = page(chromePage.class);
    private final SimpleReport report = new SimpleReport();
    @Before
    public void beforeTest(Scenario scenario) {
        log.info("cucumber start-------------------------" + scenario.getName());
        report.start();
        beforeClass();
        Configuration.timeout = 4000;//default is 4000
        Configuration.driverManagerEnabled = true;

        Configuration.reportsFolder = "target/cucumber-report/";
        Configuration.holdBrowserOpen = true;

    }

    @After
    public void afterTest(Scenario scenario) {
        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        log.info("-----Starting Scenario: \""+ scenario.getName() +"\" with Session ID: " + sessionId);
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                screenshot("Error_" + scenario.getName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        log.info("cucumber end---------------------------" + scenario.getName());
        report.finish(scenario.getName());
    }

    @Given("application URL isready")
    public void goto_baidu() throws InterruptedException {
        log.info("---1 goto_baidu");
        cpage.goto_url();
        Thread.sleep(2000);
    }
    @When("user enters the URL")
    public void user_enters_the_url() throws InterruptedException {
        log.info("---2 search");
        cpage.input_search("selenide");
        Thread.sleep(2000);
    }
    @Then("application page loads")
    public void application_page_loads() throws InterruptedException {
        log.info("---3 display");
        cpage.enter_click();
        Thread.sleep(2000);
    }
}
