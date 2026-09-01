package com.orangehrm.pages;

import com.orangehrm.utils.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By errorMsg = By.cssSelector(".oxd-alert-content-text");
    private final By requiredMsgs = By.cssSelector(".oxd-input-field-error-message");

    @Step("Open Login Page")
    public LoginPage open() {
        driver.get(ConfigReader.getBaseUrl() + "auth/login");
        waitVisible(username);
        return this;
    }

    @Step("Login with {username}")
    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }

    @Step("Click Login button")
    public void clickLogin() {
        click(loginBtn);
    }

    public boolean isInvalidCredentialsShown() {
        return isDisplayed(errorMsg) && getText(errorMsg).contains("Invalid credentials");
    }

    public boolean areRequiredMessagesShown() {
        return waitAllVisible(requiredMsgs).size() >= 2;
    }
}