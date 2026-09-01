package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class PimEmployeeListPage extends BasePage {

    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By employeeList = By.xpath("//a[text()='Employee List']");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By searchBtn = By.xpath("//button[@type='submit']");
    private final By noRecords = By.xpath("//span[text()='No Records Found']");
    private final By resultRows = By.cssSelector(".oxd-table-body .oxd-table-row");

    @Step("Navigate to PIM > Employee List")
    public PimEmployeeListPage goToEmployeeList() {
        click(pimMenu);
        click(employeeList);
        waitVisible(employeeNameInput);
        return this;
    }

    @Step("Search employee: {name}")
    public void searchEmployee(String name) {
        type(employeeNameInput, name);
        // wait for autocomplete if needed
        employeeNameInput.findElement(By.xpath(".")).sendKeys(Keys.ENTER); // optional
        click(searchBtn);
    }

    public boolean isEmployeeFound(String name) {
        return waitAllVisible(resultRows).stream()
                .anyMatch(row -> row.getText().contains(name));
    }

    public boolean isNoRecordsFound() {
        return isDisplayed(noRecords);
    }
}