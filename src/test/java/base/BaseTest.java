package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.ConfigReader;

/**
 * Every test class extends this class.
 * @BeforeMethod runs before EACH @Test method -> opens a fresh browser.
 * @AfterMethod runs after EACH @Test method -> logs pass/fail and closes the browser.
 * Because DriverFactory uses ThreadLocal, this works safely even when
 * multiple test classes run in parallel threads.
 */
public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl() + "auth/login");
        logger.info("Browser opened and navigated to login page on thread: "
                + Thread.currentThread().getId());
    }

    /**
     * Convenience helper so test classes can quickly get to a logged-in state
     * for scenarios that require authentication (PIM, Admin modules, etc.)
     */
    protected void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login("Admin", "admin123");
        logger.info("Logged in as Admin");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: " + result.getName(), result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test PASSED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test SKIPPED: " + result.getName());
        }
        DriverFactory.quitDriver();
        logger.info("Browser closed on thread: " + Thread.currentThread().getId());
    }
}
