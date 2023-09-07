package org.automator.rakesh;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTest extends TestBase {
    AndroidDriver driver;
    NrSignupPage signupPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = getDriver();
        signupPage = new NrSignupPage(driver);
    }

    @Test(dataProviderClass = NrMembersDataProviders.class, dataProvider = "loginDataProvider")
    public void testLoginPageLoad(String strMessage, String strUsername, String strPassword, String strError) {
        System.out.println("Login with: "+strUsername+" pass with: "+strPassword);
        signupPage.startTest(strUsername, strPassword);
    }
}
