package web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
    public static WebDriver createWebDriver() {
        String webdriver = System.getProperty("browser", "chrome");
        if (webdriver.isEmpty() || webdriver.equals(null)) {
            webdriver = "chrome";
        }
        switch (webdriver) {
            case "firefox":
                return new FirefoxDriver((FirefoxOptions) BrowserOptions.Firefox.setOptions());
            case "chrome":
                return new ChromeDriver((ChromeOptions) BrowserOptions.Chrome.setOptions());
            default:
                throw new RuntimeException("Unsupported webdriver: " + webdriver);
        }
    }

    private enum BrowserOptions {

        Chrome, Firefox;

        @SuppressWarnings("unchecked")
        public <T> T setOptions() {
            switch (this) {
                case Chrome:
                    String chromedriverPath = "/src/test/resources/chromedriver";
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + chromedriverPath);

                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    return (T) chromeOptions;
                case Firefox:
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.setProfile(new FirefoxProfile());
                    ffOptions.addPreference("dom.webnotifications.enabled", false);
                    return (T) ffOptions;
                default:
                    return null;
            }
        }
    }
}