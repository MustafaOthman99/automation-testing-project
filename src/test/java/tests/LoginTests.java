package tests;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.JsonDataProvider;

import java.util.List;

public class LoginTests extends BaseTest {

    @Test(dataProvider = "validLogin", dataProviderClass = JsonDataProvider.class)
    @Description("TC1: Login with valid credentials")
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        performLogin(loginPage, username, password);

        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("/dashboard/index"),
                "URL should contain /dashboard/index after valid login");
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed(),
                "Dashboard header should be displayed");
    }

    @Test(dataProvider = "invalidLogins", dataProviderClass = JsonDataProvider.class)
    @Description("TC2: Login with invalid credentials")
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        performLogin(loginPage, username, password);

        String error = loginPage.getInvalidCredentialsError();
        Assert.assertTrue(error.contains("Invalid credentials"),
                "Expected 'Invalid credentials' error, but got: " + error);
    }

    @Test
    @Description("TC3: Login with empty fields shows Required validation")
    public void testEmptyFieldsLogin() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        performLogin(loginPage, "", "");

        List<String> requiredMessages = loginPage.getRequiredMessages();
        Assert.assertEquals(requiredMessages.size(), 2,
                "Expected 2 'Required' messages (username and password)");
        requiredMessages.forEach(msg ->
                Assert.assertEquals(msg.trim(), "Required"));
    }

    @Step("Login with username={0}")
    private void performLogin(LoginPage loginPage, String username, String password) {
        logger.info("Attempting login with username='" + username + "'");
        loginPage.login(username, password);
    }
}
