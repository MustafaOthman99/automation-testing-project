package tests;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.AdminUserPage;
import pages.DashboardPage;

import java.util.List;
import java.util.Set;

public class AdminAndUiTests extends BaseTest {

    @Test
    @Description("TC9: Verify Admin > Add User page has all required fields")
    public void testAdminAddUserPage() {
        loginAsAdmin();
        AdminUserPage adminUserPage = new AdminUserPage(DriverFactory.getDriver());
        adminUserPage.openAdminModule();
        adminUserPage.clickAdd();

        Assert.assertTrue(adminUserPage.isUserRoleFieldDisplayed(), "User Role field should be displayed");
        Assert.assertTrue(adminUserPage.isEmployeeNameFieldDisplayed(), "Employee Name field should be displayed");
        Assert.assertTrue(adminUserPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        Assert.assertTrue(adminUserPage.isPasswordFieldDisplayed(), "Password field should be displayed");
    }

    @Test
    @Description("TC10: Verify OrangeHRM footer branding link")
    public void testFooterBrandingLink() {
        loginAsAdmin();
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

        Assert.assertTrue(dashboardPage.getFooterText().contains("OrangeHRM, Inc"),
                "Footer text should contain 'OrangeHRM, Inc'");

        WebDriver driver = DriverFactory.getDriver();
        String originalWindow = driver.getWindowHandle();
        dashboardPage.clickFooterLink();

        // Wait for and switch to the newly opened tab
        Set<String> windows = driver.getWindowHandles();
        for (String handle : windows) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm.com"),
                "New tab URL should contain orangehrm.com, actual: " + driver.getCurrentUrl());
    }

    @Test
    @Description("TC11: Verify sidebar menu contains all expected items")
    public void testSidebarMenu() {
        loginAsAdmin();
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

        List<String> expectedItems = List.of(
                "Admin", "PIM", "Leave", "Time", "Recruitment",
                "My Info", "Performance", "Dashboard", "Directory"
        );
        List<String> actualItems = dashboardPage.getSidebarMenuNames();

        for (String expected : expectedItems) {
            Assert.assertTrue(actualItems.contains(expected),
                    "Sidebar should contain menu item: " + expected);
        }
    }
}
