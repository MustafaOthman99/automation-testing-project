package Tests;

import Base.BaseTest;
import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.JsonDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OrangeHRM")
@Feature("Login")
public class LoginTests extends BaseTest {

    @Test(dataProvider = "validLogin", dataProviderClass = JsonDataProvider.class)
    @Story("TC1")
    @Description("Login with valid credentials and land on Dashboard")
    public void validLogin(String username, String password) {
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        Assert.assertTrue(login.getUrl().contains("/dashboard/index"),
                "URL should contain /dashboard/index");
        Assert.assertTrue(new DashboardPage(driver).isDashboardHeaderDisplayed(),
                "Dashboard header should be displayed");
    }

    @Test(dataProvider = "invalidLogins", dataProviderClass = JsonDataProvider.class)
    @Story("TC2")
    @Description("Login with invalid credentials shows error")
    public void invalidLogin(String username, String password) {
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        Assert.assertEquals(login.getErrorMessage(), "Invalid credentials",
                "Error message should be Invalid credentials");
    }

    @Test
    @Story("TC3")
    @Description("Login with empty fields shows Required under both fields")
    public void emptyLogin() {
        LoginPage login = new LoginPage(driver);
        login.clickLogin();

        Assert.assertTrue(login.isRequiredAppeared(),
                "Required validation should appear under username and password");
    }
}
