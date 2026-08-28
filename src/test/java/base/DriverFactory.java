package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

/**
 * Creates and stores one independent WebDriver instance PER THREAD.
 *
 * Why ThreadLocal?
 * When TestNG runs classes in parallel (parallel="tests", thread-count=2-3),
 * several test classes execute at the same time on different threads.
 * If we used one normal "static WebDriver driver" field, all threads would
 * fight over the SAME browser instance -> tests interfere with each other
 * and randomly fail. ThreadLocal<WebDriver> gives every thread its OWN
 * driver, completely isolated from the others.
 */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigReader.getBrowser();

        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
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
