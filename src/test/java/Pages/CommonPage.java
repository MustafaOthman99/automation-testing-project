package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonPage extends BasePage {

    private final By menuItemNames = By.cssSelector("span.oxd-main-menu-item--name");
    private final By footerLink = By.xpath("//a[contains(normalize-space(),'OrangeHRM, Inc')]");
    private final By footerText = By.cssSelector(".oxd-layout-footer");

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open sidebar menu: {menuName}")
    public void openMenu(String menuName) {
        By menu = By.xpath("//span[text()='" + menuName + "']/ancestor::a");
        click(menu);
        waitForLoaderToDisappear();
    }

    @Step("Get sidebar menu item names")
    public List<String> getSidebarMenuNames() {
        waitVisible(menuItemNames);
        return driver.findElements(menuItemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Get footer text")
    public String getFooterText() {
        WebElement footer = waitVisible(footerText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
        return footer.getText();
    }

    @Step("Click OrangeHRM, Inc footer link and read new tab URL")
    public String clickFooterBrandAndGetNewTabUrl() {
        String original = driver.getWindowHandle();
        WebElement link = waitVisible(footerLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
        click(footerLink);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(original)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(d -> d.getCurrentUrl() != null && !d.getCurrentUrl().isBlank());
        String url = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(original);
        return url;
    }
}
