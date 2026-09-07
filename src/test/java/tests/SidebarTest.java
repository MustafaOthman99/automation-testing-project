package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SidebarPage;
import utilities.JsonDataReader;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;


@Epic("OrangeHRM")
@Feature("UI")
public class SidebarTest extends BaseTest {

    @Story("TC11 - Sidebar Menu")
    @Description("Verify sidebar menu contains Admin, PIM, Leave, Time, Recruitment, My Info, Performance, Dashboard, Directory")
    @Test
    public void verifySidebarMenuTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        SidebarPage sidebarPage = new SidebarPage(getDriver());

        String username = JsonDataReader.getData("validLogin", "username");
        String password = JsonDataReader.getData("validLogin", "password");

        loginPage.login(username, password);

        Assert.assertTrue(sidebarPage.isAdminDisplayed(), "Admin menu is not displayed");
        Assert.assertTrue(sidebarPage.isPimDisplayed(), "PIM menu is not displayed");
        Assert.assertTrue(sidebarPage.isLeaveDisplayed(), "Leave menu is not displayed");
        Assert.assertTrue(sidebarPage.isTimeDisplayed(), "Time menu is not displayed");
        Assert.assertTrue(sidebarPage.isRecruitmentDisplayed(), "Recruitment menu is not displayed");
        Assert.assertTrue(sidebarPage.isMyInfoDisplayed(), "My Info menu is not displayed");
    }
}