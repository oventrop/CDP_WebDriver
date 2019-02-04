package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"mailbox:login\"]")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id=\"mailbox:password\"]")
    private WebElement passField;

    @FindBy (xpath = "//*[@class=\"o-control\" and @type = \"submit\"]")
    private WebElement submitBtn;

    private void loginInput(String login) {
        loginField.clear();
        loginField.sendKeys(login);
    }

    private void passwordInput(String pass) {
        passField.clear();
        passField.sendKeys(pass);
    }

    public MailBoxPage loginToMailbox(String login, String pass) {
        loginInput(login);
        passwordInput(pass);
        submitBtn.click();
        return new MailBoxPage(driver);
    }

    @Override
    public boolean isPageLoaded (){
        return isElementPresent(submitBtn);
    }

    @Override
    public void waitForPageLoad() {
        waitForElement(loginField, 10);
    }
}
