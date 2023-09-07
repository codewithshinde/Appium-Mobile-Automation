package org.automator.rakesh;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class NrSignupPage extends PageBase {

    By loginButton = By.xpath("//android.widget.Button[@content-desc='Login']");
    By signupRedirectBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"button-sign-up-container\"]/android.view.ViewGroup/android.widget.TextView");

    By emailInput = By.xpath("//android.widget.EditText[@content-desc=\"input-email\"]");
    By passwordInput = By.xpath("//android.widget.EditText[@content-desc=\"input-password\"]");

    By confirmPasswordInput = By.xpath("//android.widget.EditText[@content-desc=\"input-repeat-password\"]");
    By signupSubmitButton = By.xpath("//android.view.ViewGroup[@content-desc=\"button-SIGN UP\"]/android.view.ViewGroup");


    By formButton= By.xpath("//android.widget.Button[@content-desc='Forms']");
    By swipeButton = By.xpath("//android.widget.Button[@content-desc='Swipe']");


    By loginSubmitBtn = By.xpath("//android.view.ViewGroup[@content-desc=\"button-LOGIN\"]/android.view.ViewGroup");


    public NrSignupPage(AndroidDriver driver) {
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

    public void goToSignup() {
        try {
            waitVisibility(signupRedirectBtn, "Click of login btn");
            clickElement(signupRedirectBtn, "Login Button");
        } catch (Exception e) {
            System.out.println("goToLogin Exception Message: " + e.getMessage());
        }
    }


    public void startTest(String strUsername, String strPassword) {
        try {
            goToLogin();
            Thread.sleep(2000);
            goToSignup();
            Thread.sleep(2000);
            writeTextToElement(emailInput, strUsername, "Email", true);
            writeTextToElement(passwordInput, strPassword, "password", true);
            writeTextToElement(confirmPasswordInput, strPassword, "confirm Password", true);
            clickElement(signupSubmitButton, "Signup submit");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
