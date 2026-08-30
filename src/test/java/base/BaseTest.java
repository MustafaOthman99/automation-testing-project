package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.ConfigReader;
import Utils.DriverManager;
import Utils.JsonDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        log.info("Starting browser: {}", ConfigReader.getBrowser());
        DriverManager.setDriver(ConfigReader.getBrowser());
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getBaseUrl() + "auth/login");
        log.info("Opened login page");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Closing browser");
        DriverManager.quitDriver();
    }

    protected void loginAsAdmin() {
        log.info("Login with valid credentials from testdata.json");
        new LoginPage(driver).login(JsonDataProvider.validUsername(), JsonDataProvider.validPassword());
        new DashboardPage(driver).waitUntilLoaded();
    }
}
