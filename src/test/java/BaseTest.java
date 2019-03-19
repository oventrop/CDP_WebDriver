import business.UserFactory;
import business.users.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.BasePage;
import pageobjects.switcher.Switcher;
import webdriver.DriverFactory;

import static utility.SimpleLogger.logger;

public class BaseTest {

    private DriverFactory driverFactory;
    WebDriver webDriver;

    @Parameters({ "browser", "labrun" })
    @BeforeTest
    public void initDriver(String browser, boolean isLabRun) {
        driverFactory = DriverFactory.getFactoryInstance(browser, isLabRun);
        webDriver = driverFactory.getDriver();
    }

    @AfterSuite
    public void tearDown() {
        driverFactory.tearDown();
    }

    @AfterTest
    public void closeBrowser() {
        driverFactory.close();
    }

    void getUrl(String url) {
        webDriver.get(url);
    }

    <CurrentPage extends BasePage> CurrentPage getCurrentPage() {
        pauseForNewUrlAppears(3000);
        return new Switcher(webDriver).getPage();
    }

    private void pauseForNewUrlAppears(int timeout) {
        try {
            logger.info("Wait for URL");
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            logger.error("Failed to pause thread!");
            e.printStackTrace();
        }
    }

    public static User getTestUser(String name) {
        UserFactory factory = new UserFactory();
        return factory.getUser(name);
    }
}
