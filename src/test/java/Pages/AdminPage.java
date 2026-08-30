package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By userRoleLabel = By.xpath("//label[normalize-space()='User Role']");
    private final By employeeNameLabel = By.xpath("//label[normalize-space()='Employee Name']");
    private final By usernameLabel = By.xpath("//label[normalize-space()='Username']");
    private final By passwordLabel = By.xpath("//label[normalize-space()='Password']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click Add on User Management")
    public void clickAdd() {
        click(addButton);
        waitForLoaderToDisappear();
        waitVisible(userRoleLabel);
    }

    @Step("Check User Role field is displayed")
    public boolean isUserRoleDisplayed() {
        return isDisplayed(userRoleLabel);
    }

    @Step("Check Employee Name field is displayed")
    public boolean isEmployeeNameDisplayed() {
        return isDisplayed(employeeNameLabel);
    }

    @Step("Check Username field is displayed")
    public boolean isUsernameDisplayed() {
        return isDisplayed(usernameLabel);
    }

    @Step("Check Password field is displayed")
    public boolean isPasswordDisplayed() {
        return isDisplayed(passwordLabel);
    }
}
