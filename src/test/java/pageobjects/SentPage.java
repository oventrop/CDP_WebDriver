package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SentPage extends MailBoxPage {

    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isDraftMessagePresent(String to, String subj) {
        List<WebElement> drafts = driver.findElements(By.xpath("//*[@data-subject=\"" + to + "\" and data-title=\"" + subj + "\"]"));
        return !drafts.isEmpty();
    }

    public NewEmailPage openSavedEmail(String to, String subj) {
        driver.findElement(By.xpath("//*[@data-subject=\"" + subj + "\" and data-title=\"" + to + "\"]")).click();
        return new NewEmailPage(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return driver.getCurrentUrl().contains("sent");
    }
}
