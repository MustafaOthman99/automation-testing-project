package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AddEmployeePage extends BasePage {

    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By addEmployee = By.xpath("//a[text()='Add Employee']");
    private final By firstName = By.name("firstName");
    private final By lastName = By.name("lastName");
    private final By saveBtn = By.xpath("//button[@type='submit']");
    private final By requiredError = By.cssSelector(".oxd-input-field-error-message");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");

    @Step("Open Add Employee page")
    public AddEmployeePage open() {
        click(pimMenu);
        click(addEmployee);
        waitVisible(firstName);
        return this;
    }

    public boolean isAddEmployeePageOpened() {
        return driver.getCurrentUrl().contains("/pim/addEmployee")
                && isDisplayed(firstName) && isDisplayed(lastName);
    }

    @Step("Fill employee: {first} {last}")
    public void fillEmployee(String first, String last) {
        type(firstName, first);
        type(lastName, last);
    }

    @Step("Save employee")
    public void save() {
        click(saveBtn);
    }

    public boolean isRequiredErrorShown() {
        return isDisplayed(requiredError);
    }

    public boolean isPersonalDetailsOpened() {
        return isDisplayed(personalDetailsHeader);
    }
}