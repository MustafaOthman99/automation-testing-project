package tests;

import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;
import pages.LoginPage;
import utilities.JsonDataReader;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import java.time.Duration;


@Epic("OrangeHRM")
@Feature("UI")
public class FooterTest extends BaseTest {

    @Story("TC10 - Footer Branding Link")
    @Description("Verify OrangeHRM footer link is displayed and navigates to orangehrm.com")
    @Test
    public void verifyOrangeHRMFooterLinkTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        String username = JsonDataReader.getData("validLogin", "username");
        String password = JsonDataReader.getData("validLogin", "password");

        loginPage.login(username, password);

        FooterPage footerPage = new FooterPage(getDriver());
        footerPage.scrollToFooter();
        Assert.assertTrue(footerPage.isFooterDisplayed(), "OrangeHRM footer link is not displayed");
        Assert.assertTrue(footerPage.getFooterText().contains("OrangeHRM, Inc"), "Footer text does not contain 'OrangeHRM, Inc'");

        String currentWindow = getDriver().getWindowHandle();
        footerPage.clickOrangeHRMLink();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }
}