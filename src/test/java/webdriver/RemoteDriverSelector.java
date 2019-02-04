package webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverSelector {
    private WebDriver wd;
    private static final String HUB_URL = "http://localhost:4444/wd/hub";

    public WebDriver getDriver(String browser, Platform platform) {
        switch (browser) {
            case "firefox":
                try {
                    DesiredCapabilities caps = DesiredCapabilities.firefox();
                    caps.setPlatform(platform);
                    wd = new RemoteWebDriver(new URL(HUB_URL), caps);
                } catch (UnreachableBrowserException | MalformedURLException e) {
                    System.out.println("Server/hub is unavaliable, please try later");
                }
                break;
            case "chrome":
                try {
                    DesiredCapabilities caps = DesiredCapabilities.chrome();
                    caps.setPlatform(platform);
                    wd = new RemoteWebDriver(new URL(HUB_URL), caps);
                } catch (UnreachableBrowserException | MalformedURLException e) {
                    System.out.println("Server/hub is unavaliable, please try later");
                }
                break;
            default:
                throw new IllegalArgumentException("Selected browser is unavailable");
        }
        return wd;
    }
}
