package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.TestDataProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;


@Epic("OrangeHRM")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Story("TC1 - Valid Login")
    @Description("Login with valid credentials and verify Dashboard is displayed")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void validLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard header is not displayed");
        Assert.assertEquals(dashboardPage.getDashboardHeader(), "Dashboard", "Dashboard header text mismatch");
    }

    @Story("TC2 - Invalid Login")
    @Description("Login with invalid credentials and verify error message")
    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class)
    public void invalidLoginTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);
    }

    @Story("TC3 - Empty Fields Login")
    @Description("Login with empty username and password fields")
    @Test
    public void emptyFieldsLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLogin();

        Assert.assertEquals(loginPage.getUsernameRequiredMessage(), "Required", "Username required message mismatch");
        Assert.assertEquals(loginPage.getPasswordRequiredMessage(), "Required", "Password required message mismatch");
    }
}


