package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By errorMsg = By.cssSelector(".oxd-alert-content-text");
    private final By requiredMsgs = By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username: {user}")
    public void enterUsername(String user) {
        waitVisible(username);
        type(username, user);
    }

    @Step("Enter password")
    public void enterPassword(String pass) {
        type(password, pass);
    }

    @Step("Click Login")
    public void clickLogin() {
        waitVisible(username);
        click(loginBtn);
    }

    @Step("Login with username: {user}")
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    @Step("Get invalid credentials error message")
    public String getErrorMessage() {
        return getText(errorMsg);
    }

    @Step("Check Required validation under username and password")
    public boolean isRequiredAppeared() {
        return waitAllVisible(requiredMsgs).size() >= 2;
    }

    @Step("Get current URL")
    public String getUrl() {
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
        return driver.getCurrentUrl();
    }
}
