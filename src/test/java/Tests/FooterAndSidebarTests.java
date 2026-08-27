package Tests;

import Base.BaseTest;
import Pages.CommonPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Epic("OrangeHRM")
@Feature("UI")
public class FooterAndSidebarTests extends BaseTest {

    @Test
    @Story("TC10")
    @Description("Verify footer branding link opens orangehrm.com in a new tab")
    public void verifyFooterBrandingLink() {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);

        Assert.assertTrue(common.getFooterText().contains("OrangeHRM, Inc"),
                "Footer should contain OrangeHRM, Inc");

        String newTabUrl = common.clickFooterBrandAndGetNewTabUrl();
        Assert.assertTrue(newTabUrl.toLowerCase().contains("orangehrm.com"),
                "New tab URL should contain orangehrm.com. Actual: " + newTabUrl);
    }

    @Test
    @Story("TC11")
    @Description("Verify sidebar menu contains required items")
    public void verifySidebarMenu() {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        List<String> menu = common.getSidebarMenuNames();

        String[] expected = {
                "Admin", "PIM", "Leave", "Time", "Recruitment",
                "My Info", "Performance", "Dashboard", "Directory"
        };

        for (String item : expected) {
            Assert.assertTrue(menu.contains(item),
                    "Sidebar should contain: " + item + " | actual menu: " + menu);
        }
    }
}
