package com.orangehrm.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By sidebarItems = By.cssSelector(".oxd-main-menu-item span");
    private final By footerText = By.cssSelector(".oxd-layout-footer p");
    private final By footerLink = By.cssSelector(".oxd-layout-footer a");

    public boolean isDashboardDisplayed() {
        return isDisplayed(dashboardHeader);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Get sidebar menu items")
    public List<String> getSidebarMenuItems() {
        return waitAllVisible(sidebarItems).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isFooterContainsOrangeHRM() {
        scrollTo(footerText);
        return getText(footerText).contains("OrangeHRM, Inc");
    }

    @Step("Click OrangeHRM, Inc footer link")
    public void clickFooterLink() {
        scrollTo(footerLink);
        click(footerLink);
    }
}