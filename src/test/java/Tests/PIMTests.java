package Tests;

import Base.BaseTest;
import Pages.AddEmployeePage;
import Pages.CommonPage;
import Pages.PIMPage;
import Utils.JsonDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OrangeHRM")
@Feature("PIM")
public class PIMTests extends BaseTest {

    @Test(dataProvider = "existingEmployee", dataProviderClass = JsonDataProvider.class)
    @Story("TC4")
    @Description("Search for an existing employee in PIM Employee List")
    public void searchExistingEmployee(String employeeName) {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        PIMPage pim = new PIMPage(driver);

        common.openMenu("PIM");
        pim.openEmployeeList();
        pim.searchEmployee(employeeName);

        Assert.assertTrue(pim.resultsContain(employeeName),
                "Results table should contain employee: " + employeeName);
    }

    @Test(dataProvider = "nonExistingEmployee", dataProviderClass = JsonDataProvider.class)
    @Story("TC5")
    @Description("Search for a non-existing employee shows No Records Found")
    public void searchNonExistingEmployee(String employeeName) {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        PIMPage pim = new PIMPage(driver);

        common.openMenu("PIM");
        pim.openEmployeeList();
        pim.searchEmployee(employeeName);

        Assert.assertTrue(pim.isNoRecordsFoundDisplayed(),
                "No Records Found should be displayed");
    }

    @Test
    @Story("TC6")
    @Description("Open Add Employee page and verify URL and name fields")
    public void openAddEmployeePage() {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        PIMPage pim = new PIMPage(driver);
        AddEmployeePage addEmployee = new AddEmployeePage(driver);

        common.openMenu("PIM");
        pim.openAddEmployee();

        Assert.assertTrue(addEmployee.getUrl().contains("/pim/addEmployee"),
                "URL should contain /pim/addEmployee");
        Assert.assertTrue(addEmployee.isFirstNameDisplayed(), "First Name field should be displayed");
        Assert.assertTrue(addEmployee.isLastNameDisplayed(), "Last Name field should be displayed");
    }

    @Test
    @Story("TC7")
    @Description("Add employee with empty First Name shows Required")
    public void addEmployeeMissingFirstName() {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        PIMPage pim = new PIMPage(driver);
        AddEmployeePage addEmployee = new AddEmployeePage(driver);

        common.openMenu("PIM");
        pim.openAddEmployee();
        addEmployee.enterLastNameOnly("Othman");
        addEmployee.clickSave();

        Assert.assertEquals(addEmployee.getFirstNameRequiredError(), "Required",
                "First Name should show Required validation");
    }

    @Test(dataProvider = "newEmployee", dataProviderClass = JsonDataProvider.class)
    @Story("TC8")
    @Description("End-to-end: add a new employee then find them in Employee List")
    public void addNewEmployeeSuccessfully(String firstName, String lastName) {
        String uniqueLastName = lastName + System.currentTimeMillis();

        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        PIMPage pim = new PIMPage(driver);
        AddEmployeePage addEmployee = new AddEmployeePage(driver);

        common.openMenu("PIM");
        pim.openAddEmployee();
        addEmployee.enterEmployeeName(firstName, uniqueLastName);
        addEmployee.clickSave();

        Assert.assertTrue(addEmployee.isPersonalDetailsOpened(),
                "Personal Details page should open after save");

        pim.openEmployeeList();
        pim.searchEmployee(firstName + " " + uniqueLastName);

        Assert.assertTrue(pim.resultsContain(firstName) && pim.resultsContain(uniqueLastName),
                "New employee should appear in Employee List results");
    }
}
