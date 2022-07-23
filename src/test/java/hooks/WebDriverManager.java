package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SimpleReport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.PreDestroy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager {

    private final SimpleReport report = new SimpleReport();
    @Before
    public void beforeTest(Scenario scenario){
        scenario.log("--------start-----"+scenario.getName());
        report.start();
        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        System.out.println("-----Starting Scenario: \""+ scenario.getName() +"\" with Session ID: " + sessionId);
        //logger.info("-----Starting Scenario: \""+ scenario.getName() +"\" with Session ID: " + sessionId);
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 4000;//default is 4000
//        Configuration.baseUrl = "http://" + environmentConfig.uiBackendHost() +":" + environmentConfig.uiBackendPort();
        Configuration.driverManagerEnabled = true;
        Configuration.reportsFolder = "target/selenide-reports/tests";//default is build/reports/tests










    }




    @After
    public void afterTest(Scenario scenario){
        scenario.log("--------end-----"+scenario.getName());
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png","aa");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }




        report.finish(scenario.getName());
    }






    private WebDriver webDriver;

    public WebDriver getDriver() {
        return webDriver;
    }

//    @Bean(destroyMethod = "quit")
    public WebDriver getWebDriver(){
        String currentWebDriver = System.getProperty("browser", "");
        switch(currentWebDriver) {
            case ("chrome"):
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("marionette", true);
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case ("chromeHeadless"):
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                ChromeOptions chromeHeadless = new ChromeOptions();
                chromeHeadless.setHeadless(true);
                webDriver = new ChromeDriver(chromeHeadless);
                break;
            case ("iexplorer"):
                System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
//                DesiredCapabilities capabilitiesIE = DesiredCapabilities.internetExplorer();
//                capabilitiesIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//                webDriver = new InternetExplorerDriver(capabilitiesIE);
//                break;
            case ("edge"):
                System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
                webDriver = new EdgeDriver();
                break;
            case ("opera"):
                System.setProperty("webdriver.opera.driver", "operadriver.exe");
                webDriver = new OperaDriver();
                break;
            default:
                System.getProperty("browser", "chrome");
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
        }
        /**
         * Using Selenide driver:
         *
         * WebDriverRunner.setWebDriver(webDriver);
         */
        WebDriverRunner.setWebDriver(webDriver);
        return webDriver;
    }

    @PreDestroy
    public void closeSession(){
        getDriver().manage().deleteAllCookies();
        getDriver().close();
        getDriver().quit();
    }
}
