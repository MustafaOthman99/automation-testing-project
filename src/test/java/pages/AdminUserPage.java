package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminUserPage extends BasePage {

    private final By adminMenuLink = By.xpath("//span[text()='Admin']");
    private final By addButton = By.xpath("//button[text()=' Add ']");
    private final By userRoleLabel = By.xpath("//label[text()='User Role']");
    private final By employeeNameLabel = By.xpath("//label[text()='Employee Name']");
    private final By usernameLabel = By.xpath("//label[text()='Username']");
    private final By passwordLabel = By.xpath("//label[text()='Password']");

    public AdminUserPage(WebDriver driver) {
        super(driver);
    }

    public void openAdminModule() {
        click(adminMenuLink);
    }

    public void clickAdd() {
        click(addButton);
    }

    public boolean isUserRoleFieldDisplayed() {
        return isDisplayed(userRoleLabel);
    }

    public boolean isEmployeeNameFieldDisplayed() {
        return isDisplayed(employeeNameLabel);
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameLabel);
    }

    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(passwordLabel);
    }
}
