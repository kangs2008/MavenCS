package lib;

import chromeSteps.chromeSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
@Slf4j
public class BasePage {
    //private static final Logger log = LoggerFactory.getLogger(BasePage.class);
    public static void beforeClass() {
        log.info("beforeClass");
        //System.out.println("beforeClass");
        Configuration.browser = ChromeBrowserProvider.class.getName();
        log.info("beforeClass2");
        //Configuration.remote = "http://127.0.0.1:4444/wd/hub";
        //Configuration.browserSize = "1920x1080";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/cucumber-reports";
        //Configuration.screenshots = false;
        //Configuration.fastSetValue = true;
        //Configuration.baseUrl = System.getProperty("base.url");
    }
}
