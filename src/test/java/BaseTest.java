import business.UserFactory;
import business.users.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.BasePage;
import pageobjects.switcher.Switcher;
import utility.logger.Logger;
import utility.logger.LoggerFactory;
import webdriver.DriverFactory;

public class BaseTest {

    private DriverFactory driverFactory;
    WebDriver webDriver;
    Logger logger;

    @Parameters({ "browser", "labrun", "enableFileLogging" })
    @BeforeTest
    public void initDriver(String browser, boolean isLabRun, boolean enableFileLogging) {
        driverFactory = DriverFactory.getInstance(browser, isLabRun);
        webDriver = driverFactory.getDriver();
        logger = LoggerFactory.getLogger(enableFileLogging);
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

    <CurrentPage extends BasePage> CurrentPage getCurrentPage (){
        pauseForNewUrlAppears(3000);
       return new Switcher(webDriver).getPage();
    }

    private void pauseForNewUrlAppears(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static User getTestUser(String name){
        UserFactory factory = new UserFactory();
        return factory.getUser(name);
    }
}
