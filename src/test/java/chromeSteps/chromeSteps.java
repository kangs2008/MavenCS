package chromeSteps;

import chromePages.chromePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.page;

public class chromeSteps {
    private static final Logger log = LoggerFactory.getLogger(chromeSteps.class);
//    private chromePage cpage = new chromePage();
    private chromePage cpage = page(chromePage.class);
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
