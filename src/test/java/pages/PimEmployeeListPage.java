package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimEmployeeListPage extends BasePage {

    private final By pimMenuLink = By.xpath("//span[text()='PIM']");
    private final By addEmployeeMenuLink = By.xpath("//a[text()='Add Employee']");
    private final By employeeNameSearchField =
            By.xpath("//label[text()='Employee Name']/../..//input");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultsTable = By.cssSelector(".oxd-table-body");
    private final By noRecordsFound = By.xpath("//span[text()='No Records Found']");

    public PimEmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public void openPimModule() {
        click(pimMenuLink);
    }

    public void clickAddEmployee() {
        click(addEmployeeMenuLink);
    }

    public void searchEmployeeByName(String name) {
        type(employeeNameSearchField, name);
        click(searchButton);
    }

    public boolean isEmployeeInResults(String name) {
        return getText(resultsTable).contains(name);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return isDisplayed(noRecordsFound);
    }
}
