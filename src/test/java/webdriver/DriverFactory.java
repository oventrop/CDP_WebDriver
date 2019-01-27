package webdriver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static DriverFactory instance;
    private String browser = "";

    private DriverFactory(String browser) {
        this.browser = browser;
    }

    public static DriverFactory getInstance(String browser) {
        return new DriverFactory(browser);
    }

    private ThreadLocal<WebDriver> webDriver = ThreadLocal.withInitial(() -> new DriverSelector().getDriver(browser));

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public void tearDown() {
        if (webDriver != null) {
            webDriver.remove();
        }
    }

    public void close() {
        getDriver().close();
    }
}
