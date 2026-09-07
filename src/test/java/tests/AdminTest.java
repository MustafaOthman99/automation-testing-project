package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import utilities.JsonDataReader;import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;

@Epic("OrangeHRM")
@Feature("Admin")
public class AdminTest extends BaseTest {

    @Story("TC9 - Add User Page")
    @Description("Verify Admin Add User page shows User Role, Employee Name, Username, and Password fields")
    @Test
    public void verifyAddUserPageTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        String username = JsonDataReader.getData("validLogin", "username");
        String password = JsonDataReader.getData("validLogin", "password");

        loginPage.login(username, password);
        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.openAdmin();
        adminPage.clickAdd();

        Assert.assertTrue(adminPage.isUserRoleDisplayed(), "User Role field is not displayed");
        Assert.assertTrue(adminPage.isEmployeeNameDisplayed(), "Employee Name field is not displayed");
        Assert.assertTrue(adminPage.isUsernameDisplayed(), "Username field is not displayed");
        Assert.assertTrue(adminPage.isPasswordDisplayed(), "Password field is not displayed");
    }
}
