package pageobjects.switcher;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;

public class Switcher {

    WebDriver webDriver;

    public Switcher(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public <CurrentPage extends BasePage> CurrentPage getPage() {
        if (webDriver.getCurrentUrl().contains("drafts")) return (CurrentPage) new DraftsPage(webDriver);
        if (webDriver.getCurrentUrl().contains("inbox")) return (CurrentPage) new MailBoxPage(webDriver);
        if (webDriver.getCurrentUrl().equals("https://mail.ru/")) return (CurrentPage) new MainPage(webDriver);
        if (webDriver.getCurrentUrl().contains("compose")) return (CurrentPage) new NewEmailPage(webDriver);
        if (webDriver.getCurrentUrl().contains("sent")) return (CurrentPage) new SentPage(webDriver);
        throw new NoSuchElementException("Unable to identify current page");
    }
}
