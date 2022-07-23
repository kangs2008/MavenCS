package chromePages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class chromePage {
    private static final Logger log = LoggerFactory.getLogger(chromePage.class);
    @FindBy(xpath = "//*[@id='kw']")
    private SelenideElement search_input;

    @FindBy(xpath = "//*[@id='su']")
    private SelenideElement enter_btn;

    public void goto_url(){
        log.info("aaaaaaaaaaaaaa");
        Configuration.browser = "chrome";
        //System.setProperty("webdriver.chrome.driver", "D:\\work\\com.test.selenium\\drivers\\chromedriver.exe");
        //LoginPages login = open("http://app-member.fx.com/login", LoginPages.class);
        log.info("aa2222aaaaaaaaaaaa");
        open("https://www.baidu.com");
//        return page(chromePage.class);
    }
    public void input_search(String str){
        log.info("bbbbbbbbbbbbbb");
        search_input.setValue(str);
    }
    public void enter_click(){
        log.info("ccccccccccccc");
        enter_btn.click();
    }
}

