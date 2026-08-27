package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait until Dashboard is loaded")
    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    @Step("Assert Dashboard header is displayed")
    public boolean isDashboardHeaderDisplayed() {
        return isDisplayed(dashboardHeader);
    }
}
