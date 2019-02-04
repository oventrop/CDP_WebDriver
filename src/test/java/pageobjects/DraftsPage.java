package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DraftsPage extends MailBoxPage {

    public DraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-name=\"remove\"]")
    private WebElement deleteEmail;

    public boolean isDraftMessagePresent(String to, String subj) {
        String query = "//*[@data-subject=\"" + subj + "\" and @title=\"" + to + "\"]";
        List<WebElement> drafts = driver.findElements(By.xpath(query));
        return !drafts.isEmpty();
    }

    public NewEmailPage openSavedEmail(String to, String subj) {
        String query = "//*[@data-subject=\"" + subj + "\" and @title=\"" + to + "\"]";
        driver.findElements(By.xpath(query)).get(0).click();
        return new NewEmailPage(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return isElementPresent(deleteEmail);
    }
}
