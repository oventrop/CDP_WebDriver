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

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            return new DriverSelector().getDriver(browser);
        }
    };

    public WebDriver getDriver() {
        return driver.get();
    }

    public void tearDown() {
        if (driver != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public void close() {
        getDriver().close();
    }
}
