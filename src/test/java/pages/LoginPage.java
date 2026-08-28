package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LoginPage extends BasePage {

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By invalidCredsError = By.cssSelector(".oxd-alert-content-text");
    // "Required" validation messages shown under empty fields
    private final By requiredMessages = By.cssSelector(".oxd-input-group .oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getInvalidCredentialsError() {
        return getText(invalidCredsError);
    }

    public List<String> getRequiredMessages() {
        return waitVisibleAll(requiredMessages).stream()
                .map(el -> el.getText())
                .toList();
    }
}
