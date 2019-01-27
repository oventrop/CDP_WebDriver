package webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

class DriverSelector {

    WebDriver getDriver(String browserName) {
        WebDriver wd;
        switch (browserName) {
            case "firefox":
                wd = getFirefoxDriver();
                break;
            case "chrome":
                wd = getChromeDriver();
                break;
            default:
                throw new RuntimeException("Failed to initialize driver");
        }
        return wd;
    }

    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver wd = new ChromeDriver();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setPlatform(Platform.WINDOWS);
        caps.setBrowserName("chrome");
        return wd;
    }

    private WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setPlatform(Platform.WINDOWS);
        caps.setBrowserName("firefox");
        return wd;
    }
}
