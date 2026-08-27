package Tests;

import Base.BaseTest;
import Pages.AdminPage;
import Pages.CommonPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("OrangeHRM")
@Feature("Admin")
public class AdminTests extends BaseTest {

    @Test
    @Story("TC9")
    @Description("Verify Admin Add User form fields")
    public void verifyAddUserPage() {
        loginAsAdmin();
        CommonPage common = new CommonPage(driver);
        AdminPage admin = new AdminPage(driver);

        common.openMenu("Admin");
        admin.clickAdd();

        Assert.assertTrue(admin.isUserRoleDisplayed(), "User Role should be displayed");
        Assert.assertTrue(admin.isEmployeeNameDisplayed(), "Employee Name should be displayed");
        Assert.assertTrue(admin.isUsernameDisplayed(), "Username should be displayed");
        Assert.assertTrue(admin.isPasswordDisplayed(), "Password should be displayed");
    }
}
