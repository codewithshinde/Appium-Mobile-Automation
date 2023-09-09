package org.automator.rakesh;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.time.Duration;

public class TestListner extends TestBase implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("OnTestStart");
        System.out.println("Starting TestClass: " + result.getTestClass().getRealClass().getSimpleName());
        //AndroidDriver adriver = ((TestBase) result.getInstance()).getDriver(); // Get the driver from the test class
        //setDeviceLocation(adriver, true);
        System.out.println("Starting TestMethod: " + result.getMethod().getMethodName());
    }

    public void setDeviceLocation(AndroidDriver adriver , boolean enableLocation){
            if (enableLocation) {
                System.out.println("Setting Device location");
                System.out.println(adriver.getClipboardText());
                // Launch the device settings app
                //driver.startActivity(new Activity("com.android.settings", "com.android.settings.Settings"));
                // Go back to the home screen or your app
                // driver.pressKey(new KeyEvent(AndroidKey.HOME));
            }
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess");

    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure");

    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped");

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage");

    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println("onStart");

    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish");

    }
}
