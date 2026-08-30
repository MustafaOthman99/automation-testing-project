package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {

    private final By firstNameField = By.name("firstName");
    private final By lastNameField = By.name("lastName");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By firstNameRequired =
            By.xpath("//input[@name='firstName']/parent::div/following-sibling::span");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check First Name field is displayed")
    public boolean isFirstNameDisplayed() {
        return isDisplayed(firstNameField);
    }

    @Step("Check Last Name field is displayed")
    public boolean isLastNameDisplayed() {
        return isDisplayed(lastNameField);
    }

    @Step("Enter last name only: {lastName}")
    public void enterLastNameOnly(String lastName) {
        type(lastNameField, lastName);
    }

    @Step("Enter employee first name {firstName} and last name {lastName}")
    public void enterEmployeeName(String firstName, String lastName) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
    }

    @Step("Click Save")
    public void clickSave() {
        click(saveButton);
        waitForLoaderToDisappear();
    }

    @Step("Get First Name Required error")
    public String getFirstNameRequiredError() {
        return getText(firstNameRequired);
    }

    @Step("Wait until Personal Details page opens")
    public boolean isPersonalDetailsOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader));
        return isDisplayed(personalDetailsHeader);
    }

    @Step("Get current URL")
    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
