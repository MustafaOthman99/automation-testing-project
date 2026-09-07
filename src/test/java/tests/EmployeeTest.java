package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.TestDataProvider;import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;

@Epic("OrangeHRM")
@Feature("PIM")
public class EmployeeTest extends BaseTest {

    @Story("TC4 - Search Existing Employee")
    @Description("Search for an existing employee and verify they appear in results")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void searchForEmployeeTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        PimPage pimPage = new PimPage(getDriver());
        pimPage.openEmployeeList();

        EmployeeListPage employeeListPage = new EmployeeListPage(getDriver());
        employeeListPage.enterEmployeeName("Joseph Evans");
        employeeListPage.clickSearch();

        Assert.assertTrue(employeeListPage.isEmployeeDisplayed("Joseph Evans"), "Employee 'Joseph Evans' is not displayed");
    }

    @Story("TC5 - Search Non-Existing Employee")
    @Description("Search for a non-existing employee and verify 'No Records Found' message")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void searchForNonExistingEmployeeTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        PimPage pimPage = new PimPage(getDriver());
        pimPage.openEmployeeList();

        EmployeeListPage employeeListPage = new EmployeeListPage(getDriver());
        employeeListPage.enterEmployeeName("XYZNonExisting123");
        employeeListPage.clickSearch();

        Assert.assertTrue(employeeListPage.isNoRecordsFoundDisplayed(), "No Records Found message is not displayed");
    }

    @Story("TC6 - Open Add Employee Page")
    @Description("Verify Add Employee page opens with correct URL and First Name field displayed")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void openAddEmployeePageTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is not displayed after login");

        PimPage pimPage = new PimPage(getDriver());
        pimPage.openAddEmployee();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/pim/addEmployee"), "URL does not contain /pim/addEmployee");

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
        Assert.assertTrue(addEmployeePage.isFirstNameDisplayed(), "First Name field is not displayed");
    }

    @Story("TC7 - Add Employee Missing First Name")
    @Description("Add employee with empty First Name shows Required validation message")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void addEmployeeWithEmptyFirstNameTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        PimPage pimPage = new PimPage(getDriver());
        pimPage.openAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
        addEmployeePage.enterLastName("Othman");
        addEmployeePage.clickSave();

        Assert.assertTrue(addEmployeePage.isFirstNameRequiredDisplayed(), "Required message is not displayed under First Name");
    }

    @Story("TC8 - Add New Employee Successfully")
    @Description("Add a new employee end-to-end and verify Personal Details page and search result")
    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void addEmployeeSuccessfullyTest(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        PimPage pimPage = new PimPage(getDriver());
        pimPage.openAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver());
        addEmployeePage.enterFirstName("Mustafa");
        addEmployeePage.enterLastName("Othman");
        addEmployeePage.clickSave();

        Assert.assertTrue(addEmployeePage.isPersonalDetailsDisplayed(), "Personal Details page is not displayed after saving");

        pimPage.openEmployeeList();
    }
}