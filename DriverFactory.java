
package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;


public class DriverFactory {
    private static final ThreadLocal <WebDriver> driver = new ThreadLocal<>();
    public static void initDriver() {
        String browser = ConfigReader.getBrowser();
        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(options);
                break;
        }
        driver.set(webDriver);
    }
public static WebDriver getDriver() {
        return driver.get();
    }
public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // important: clears the thread's slot to avoid memory leaks
        }
    }
}
