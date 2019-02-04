package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailBoxPage extends BasePage {

    public MailBoxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-name=\"compose\"]")
    private WebElement newMailBtn;

    @FindBy(xpath = "//*[@data-mnemo=\"drafts\"]")
    private WebElement draftsButton;

    @FindBy(xpath = "//*[@href=\"/messages/sent/\"]")
    private WebElement sentButton;

    @FindBy(xpath = "(//*[@href=\"/messages/inbox/\"])[1]")
    WebElement inboxTopBtn;

    @FindBy(xpath = "(//*[@href=\"/messages/inbox/\"])[2]")
    WebElement inboxPanelBtn;

    @Override
    public boolean isPageLoaded() {
        return isElementPresent(newMailBtn);
    }

    @Override
    public void waitForPageLoad() {
        waitForElement(newMailBtn, 10);
    }

    public DraftsPage openDrafts() {
        draftsButton.click();
        return new DraftsPage(driver);
    }

    public SentPage openSent() {
        sentButton.click();
        return new SentPage(driver);
    }

    public NewEmailPage writeNewEmail() {
        newMailBtn.click();
        return new NewEmailPage(driver);
    }
}
