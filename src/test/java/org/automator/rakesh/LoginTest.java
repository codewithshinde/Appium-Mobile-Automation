package org.automator.rakesh;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    AndroidDriver driver;
    NrLoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = getDriver();
        loginPage = new NrLoginPage(driver);
    }

    @Test(dataProviderClass = NrMembersDataProviders.class, dataProvider = "loginDataProvider")
    public void testLoginPageLoad(String strMessage, String strUsername, String strPassword, String strError) {
        System.out.println("Login with: "+strUsername+" pass with: "+strPassword);
        loginPage.startTest(strUsername, strPassword);
    }




}
