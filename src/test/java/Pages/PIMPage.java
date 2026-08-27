package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PIMPage extends BasePage {

    private final By employeeListTab = By.xpath("//a[normalize-space()='Employee List']");
    private final By addEmployeeTab = By.xpath("//a[normalize-space()='Add Employee']");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By tableBody = By.cssSelector(".oxd-table-body");
    private final By noRecords = By.xpath("//span[text()='No Records Found'] | //p[contains(.,'No Records Found')]");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Employee List")
    public void openEmployeeList() {
        click(employeeListTab);
        waitForLoaderToDisappear();
        waitVisible(employeeNameInput);
    }

    @Step("Open Add Employee from PIM top menu")
    public void openAddEmployee() {
        click(addEmployeeTab);
        waitForLoaderToDisappear();
    }

    @Step("Search employee by name: {name}")
    public void searchEmployee(String name) {
        waitVisible(employeeNameInput);
        type(employeeNameInput, name);
        selectAutocompleteIfPresent(name);
        click(searchButton);
        waitForLoaderToDisappear();
    }

    private void selectAutocompleteIfPresent(String name) {
        By option = By.cssSelector(".oxd-autocomplete-option");
        try {
            List<WebElement> options = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(option));
            for (WebElement item : options) {
                String text = item.getText();
                if (!text.contains("No Records") && text.toLowerCase().contains(name.toLowerCase())) {
                    item.click();
                    return;
                }
            }
        } catch (TimeoutException ignored) {
            // no dropdown — search by typed text
        }
    }

    @Step("Check results table contains: {name}")
    public boolean resultsContain(String name) {
        waitVisible(tableBody);
        return driver.findElement(tableBody).getText().toLowerCase().contains(name.toLowerCase());
    }

    @Step("Check No Records Found is displayed")
    public boolean isNoRecordsFoundDisplayed() {
        return isDisplayed(noRecords);
    }
}
