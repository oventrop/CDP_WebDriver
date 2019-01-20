import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.DriverFactory;

public class BaseTest {

    private DriverFactory driverFactory;
    WebDriver wd;

    @Parameters({ "browser" })
    @BeforeTest
    public void initDriver(String browser) {
        driverFactory = DriverFactory.getInstance(browser);
        wd = driverFactory.getDriver();
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
        wd.get(url);
    }
}
