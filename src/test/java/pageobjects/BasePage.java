package pageobjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //set implicit wait
        driver.manage().timeouts().implicitlyWait(20, SECONDS);
    }

    //explicit wait
    public void waitForElement(WebElement webElement, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        ExpectedCondition elementIsPresent = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    return webElement != null;
                } catch (NoSuchElementException e) {
                    return false;
                } catch (StaleElementReferenceException f) {
                    return false;
                }
            }
        };
        wait.until(elementIsPresent);
    }

    protected boolean isElementPresent(WebElement element) {
        return isElementPresent(element, 20);
    }

    protected boolean isElementPresent(WebElement element, int timeout) {
        waitForElement(element, timeout);
        return true;
    }

    public abstract boolean isPageLoaded();

    public abstract void waitForPageLoad();

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
