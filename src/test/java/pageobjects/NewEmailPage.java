package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewEmailPage extends MailBoxPage {

    public NewEmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-name=\"send\"]")
    private WebElement sendBtn;

    @FindBy(xpath = "(//*[@data-name=\"saveDraft\"])[1]")
    private WebElement saveDraftBtn;

    @FindBy(xpath = "//*[@data-original-name = \"To\"]")
    private WebElement recipientField;

    @FindBy(xpath = "//*[@name=\"Subject\"]")
    private WebElement subjectField;

    @FindBy(xpath = "//iframe")
    private WebElement bodyFrame;

    @FindBy(css = "#tinymce")
    private WebElement bodyField;

    public void saveDraft() {
        saveDraftBtn.click();
        pause(3000);
    }

    public void sendEmail() {
        sendBtn.click();
        pause(3000);
    }

    public void fillAddress(String email) {
        recipientField.clear();
        recipientField.sendKeys(email);
    }

    public void fillSubject(String subject) {
        subjectField.clear();
        subjectField.sendKeys(subject);
    }

    public void fillEmailBody(String body) {
        driver.switchTo().frame(bodyFrame);
        bodyField.sendKeys(body);
        driver.switchTo().defaultContent();
    }

    @Override
    public boolean isPageLoaded() {
        return isElementPresent(sendBtn);
    }

    public boolean isAddressFieldFilled(String expectedAddress) {
        List<WebElement> recipients = driver.findElements(By.xpath("//*[@data-text=\"" + expectedAddress + "\"]"));
        return !recipients.isEmpty();
    }
}
