package org.automater.setup;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class LauchApp {
    AndroidDriver driver;
    @BeforeTest
    public void setup() {
        try {
            UiAutomator2Options capabilities = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setPlatformVersion("11.0")
                    .setDeviceName("Pixel7")
                    .setUdid("emulator-5554")
                    .setAppPackage("com.wdiodemoapp")
                    .setAppActivity("com.wdiodemoapp.MainActivity");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver(url, capabilities);

        } catch (Exception ex) {
            System.out.println("Cause is: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
        }
    }

    @Test
    public void sampleTest() {
        System.out.println("Login Test");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc='Login']")));
            loginButton.click();
            driver.getPageSource();
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }

    }

    @AfterTest
    public void closeSetup() {

    }

}
