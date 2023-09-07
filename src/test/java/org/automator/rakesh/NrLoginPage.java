package org.automator.rakesh;

import io.appium.java_client.android.AndroidDriver;
import org.automater.setup.AppEventsObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NrLoginPage extends PageBase {
    /**
     * Login Page Mobile Elements
     */
    By loginButton = By.xpath("//android.widget.Button[@content-desc='Login']");
    By formButton= By.xpath("//android.widget.Button[@content-desc='Forms']");
    By swipeButton = By.xpath("//android.widget.Button[@content-desc='Swipe']");

    By emailInput = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
    By passwordInput = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");

    By confirmPasswordInput = By.xpath("//android.widget.EditText[@content-desc=\"input-repeat-password\"]");
    By loginSubmitBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]/android.view.ViewGroup");

    By signupRedirectBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"button-sign-up-container\"]/android.view.ViewGroup/android.widget.TextView");
    By signupSubmitButton = By.xpath("//android.view.ViewGroup[@content-desc=\"button-SIGN UP\"]/android.view.ViewGroup");

    public NrLoginPage(AndroidDriver driver) {
        super(driver);
    }

    public void goToLogin() {
        try {
            waitVisibility(loginButton, "Click of login btn");
            clickElement(loginButton, "Login Button");
        } catch (Exception e) {
            System.out.println("goToLogin Exception Message: " + e.getMessage());
        }
    }

    public void goToForm() {
        try {
            waitVisibility(formButton, "Click of login btn");
            clickElement(formButton, "Login Button");
        } catch (Exception e) {
            System.out.println("goToLogin Exception Message: " + e.getMessage());
        }
    }

    public void goToSwipe() {
        try {
            waitVisibility(swipeButton, "Click of login btn");
            clickElement(swipeButton, "Login Button");
        } catch (Exception e) {
            System.out.println("goToLogin Exception Message: " + e.getMessage());
        }
    }

    public void startTest(String strUsername, String strPassword) {
        try {
            goToLogin();
            Thread.sleep(2000);
            writeTextToElement(emailInput, strUsername, "Email", true);
            writeTextToElement(passwordInput, strPassword, "Password", true);
            clickElement(loginSubmitBtn, "Login submit");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
