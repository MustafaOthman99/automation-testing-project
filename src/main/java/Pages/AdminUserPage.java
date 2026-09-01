package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AdminUserPage extends BasePage {

    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By addBtn = By.xpath("//button[normalize-space()='Add']");
    private final By userRole = By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By employeeName = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
    private final By passwordField = By.xpath("//label[text()='Password']/following::input[1]");

    @Step("Navigate to Admin > User Management > Users and click Add")
    public AdminUserPage openAddUser() {
        click(adminMenu);
        // User Management > Users is usually default
        click(addBtn);
        waitVisible(usernameField);
        return this;
    }

    public boolean areAddUserFieldsDisplayed() {
        return isDisplayed(userRole)
                && isDisplayed(employeeName)
                && isDisplayed(usernameField)
                && isDisplayed(passwordField);
    }
}