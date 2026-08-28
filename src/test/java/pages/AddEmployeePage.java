package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage extends BasePage {

    private final By firstNameField = By.name("firstName");
    private final By lastNameField = By.name("lastName");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By firstNameRequiredError =
            By.xpath("(//input[@name='firstName']/../following-sibling::span)[1]");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public boolean isFirstNameFieldDisplayed() {
        return isDisplayed(firstNameField);
    }

    public boolean isLastNameFieldDisplayed() {
        return isDisplayed(lastNameField);
    }

    public void enterLastNameOnly(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmployeeName(String firstName, String lastName) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
    }

    public void clickSave() {
        click(saveButton);
    }

    public String getFirstNameRequiredError() {
        return getText(firstNameRequiredError);
    }

    public boolean isPersonalDetailsPageOpened() {
        return isDisplayed(personalDetailsHeader);
    }
}
