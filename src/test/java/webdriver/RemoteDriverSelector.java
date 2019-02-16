package webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverSelector {

    private WebDriver wd;
    private static final String HUB_URL = "http://localhost:4444/wd/hub";

    WebDriver getDriver(String browser, Platform platform) {
        switch (browser) {
            case "firefox":
                try {
                    FirefoxOptions options = new FirefoxOptions();
                    wd = new RemoteWebDriver(new URL(HUB_URL), options);
                } catch (UnreachableBrowserException | MalformedURLException e) {
                    System.out.println("Server/hub is unavailable, please try later");
                }
                break;
            case "chrome":
                try {
                    ChromeOptions options = new ChromeOptions();
                    wd = new RemoteWebDriver(new URL(HUB_URL), options);
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
