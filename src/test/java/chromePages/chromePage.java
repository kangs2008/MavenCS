package chromePages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import lib.BasePage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

@Slf4j
public class chromePage {
    //private static final Logger log = LoggerFactory.getLogger(chromePage.class);

    @FindBy(xpath = "//*[@id='kw']")
    private SelenideElement search_input;

    @FindBy(xpath = "//*[@id='su']")
    private SelenideElement enter_btn;

    public void goto_url(){
        log.info("goto_url");
        open("https://www.baidu.com");
        screenshot("baidu");
    }
    public void input_search(String str){
        log.info("input_search");
        search_input.setValue(str);
        screenshot("search");
    }
    public void enter_click(){
        log.info("enter_click");
        enter_btn.click();
        screenshot("click");
    }
}

