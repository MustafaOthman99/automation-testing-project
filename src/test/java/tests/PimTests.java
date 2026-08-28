package tests;

import base.BaseTest;
import base.DriverFactory;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.PimEmployeeListPage;
import utils.JsonDataProvider;

public class PimTests extends BaseTest {

    @Test(dataProvider = "existingEmployee", dataProviderClass = JsonDataProvider.class)
    @Description("TC4: Search for an existing employee in PIM")
    public void testSearchExistingEmployee(String employeeName) {
        loginAsAdmin();
        PimEmployeeListPage pim = new PimEmployeeListPage(DriverFactory.getDriver());
        pim.openPimModule();
        pim.searchEmployeeByName(employeeName);

        Assert.assertTrue(pim.isEmployeeInResults(employeeName),
                "Results table should contain employee: " + employeeName
                        + " (make sure this name exists in your demo instance)");
    }

    @Test(dataProvider = "nonExistingEmployee", dataProviderClass = JsonDataProvider.class)
    @Description("TC5: Search for a non-existing employee")
    public void testSearchNonExistingEmployee(String employeeName) {
        loginAsAdmin();
        PimEmployeeListPage pim = new PimEmployeeListPage(DriverFactory.getDriver());
        pim.openPimModule();
        pim.searchEmployeeByName(employeeName);

        Assert.assertTrue(pim.isNoRecordsFoundDisplayed(),
                "'No Records Found' message should be displayed for: " + employeeName);
    }

    @Test
    @Description("TC6: Open Add Employee page")
    public void testOpenAddEmployeePage() {
        loginAsAdmin();
        PimEmployeeListPage pim = new PimEmployeeListPage(DriverFactory.getDriver());
        pim.openPimModule();
        pim.clickAddEmployee();

        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("/pim/addEmployee"),
                "URL should contain /pim/addEmployee");

        AddEmployeePage addEmployeePage = new AddEmployeePage(DriverFactory.getDriver());
        Assert.assertTrue(addEmployeePage.isFirstNameFieldDisplayed(), "First Name field should be displayed");
        Assert.assertTrue(addEmployeePage.isLastNameFieldDisplayed(), "Last Name field should be displayed");
    }

    @Test
    @Description("TC7: Add employee with empty required First Name field")
    public void testAddEmployeeEmptyFirstName() {
        loginAsAdmin();
        PimEmployeeListPage pim = new PimEmployeeListPage(DriverFactory.getDriver());
        pim.openPimModule();
        pim.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(DriverFactory.getDriver());
        addEmployeePage.enterLastNameOnly("OnlyLastName");
        addEmployeePage.clickSave();

        Assert.assertEquals(addEmployeePage.getFirstNameRequiredError().trim(), "Required",
                "'Required' validation error should be shown under First Name");
    }

    @Test(dataProvider = "newEmployee", dataProviderClass = JsonDataProvider.class)
    @Description("TC8: End-to-end - add a new employee successfully")
    public void testAddNewEmployeeEndToEnd(String firstName, String lastName) {
        loginAsAdmin();
        PimEmployeeListPage pim = new PimEmployeeListPage(DriverFactory.getDriver());
        pim.openPimModule();
        pim.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(DriverFactory.getDriver());
        addEmployeePage.enterEmployeeName(firstName, lastName);
        addEmployeePage.clickSave();

        Assert.assertTrue(addEmployeePage.isPersonalDetailsPageOpened(),
                "Personal Details page should open for the newly created employee");

        // Navigate back to Employee List and confirm the new employee appears
        PimEmployeeListPage pimAgain = new PimEmployeeListPage(DriverFactory.getDriver());
        pimAgain.openPimModule();
        pimAgain.searchEmployeeByName(firstName + " " + lastName);

        Assert.assertTrue(pimAgain.isEmployeeInResults(firstName),
                "Newly added employee should appear in the search results");
    }
}
