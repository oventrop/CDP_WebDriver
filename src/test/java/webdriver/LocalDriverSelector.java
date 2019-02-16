package webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

class LocalDriverSelector {

    WebDriver getDriver(String browserName, Platform platform) {
        WebDriver wd;
        switch (browserName) {
            case "firefox":
                wd = getFirefoxDriver(platform);
                break;
            case "chrome":
                wd = getChromeDriver(platform);
                break;
            default:
                throw new RuntimeException("Failed to initialize driver");
        }
        return wd;
    }

    private WebDriver getChromeDriver(Platform platform) {
        if (platform.is(Platform.MAC))
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        else
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver wd = new ChromeDriver();
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setPlatform(platform);
        caps.setBrowserName("chrome");
        return wd;
    }

    private WebDriver getFirefoxDriver(Platform platform) {
        if (platform.is(Platform.MAC))
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        else
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        WebDriver wd = new FirefoxDriver();
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setPlatform(platform);
        caps.setBrowserName("firefox");
        return wd;
    }
}