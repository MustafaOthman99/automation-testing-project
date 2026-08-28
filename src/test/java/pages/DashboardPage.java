package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By sidebarMenuItems = By.cssSelector(".oxd-main-menu-item");
    private final By footerText = By.cssSelector(".oxd-layout-footer-container");
    private final By footerLink = By.linkText("OrangeHRM, Inc");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardHeaderDisplayed() {
        return isDisplayed(dashboardHeader);
    }

    public List<String> getSidebarMenuNames() {
        return waitVisibleAll(sidebarMenuItems).stream()
                .map(el -> el.getText().trim())
                .toList();
    }

    public String getFooterText() {
        return getText(footerText);
    }

    public void clickFooterLink() {
        click(footerLink);
    }
}
