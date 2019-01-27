import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.BasePage;
import pageobjects.switcher.Switcher;
import webdriver.DriverFactory;

public class BaseTest {

    private DriverFactory driverFactory;
    WebDriver webDriver;

    @Parameters({ "browser" })
    @BeforeTest
    public void initDriver(String browser) {
        driverFactory = DriverFactory.getInstance(browser);
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

    void openPage(String url) {
        webDriver.get(url);
    }

    <CurrentPage extends BasePage> CurrentPage getCurrentPage (){
        waitForNewUrlAppears(3000);
       return new Switcher(webDriver).getPage();
    }

    public void waitForNewUrlAppears(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
