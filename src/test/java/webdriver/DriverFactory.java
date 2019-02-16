package webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private String browser;
    private ThreadLocal<WebDriver> webDriver;

    private DriverFactory(String browser, boolean isLabRun) {
        this.browser = browser;
        if (isLabRun)
            webDriver = remoteWebDriver;
        else
            webDriver = localWebDriver;
    }

    public static DriverFactory getInstance(String browser, boolean isLabRun) {
        return new DriverFactory(browser, isLabRun);
    }

    private ThreadLocal<WebDriver> localWebDriver = ThreadLocal.withInitial(() -> new LocalDriverSelector().getDriver(browser, getOs()));

    private ThreadLocal<WebDriver> remoteWebDriver = ThreadLocal.withInitial(() -> new RemoteDriverSelector().getDriver(browser, getOs()));


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

    private Platform getOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return Platform.WINDOWS;
        }
        if (os.contains("mac")) {
            return Platform.MAC;
        }
        throw new RuntimeException("Unknown OS");
    }
}
