package org.automater.setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppEvents {
    AppiumDriver driver;
    public AppEvents(AppiumDriver driver) {
        this.driver = driver;
    }

    public void goToLogin() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AppEventsObjects.HOME_LOGIN_BTN)));
            loginButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("goToLogin Exception Message: " + e.getMessage());
        }
    }

    public void goToForm() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement formButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AppEventsObjects.HOME_FORM_BTN)));
            formButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("goToForm Exception Message: " + e.getMessage());
        }
    }

    public void goToSwipe() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement swipeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AppEventsObjects.HOME_SWIPE_BTN)));
            swipeButton.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("goToSwipe Exception Message: " + e.getMessage());
        }
    }

    public void visitHomeLinks() {
        try {
            goToLogin();
            goToForm();
            goToSwipe();
        } catch (Exception e) {
            System.out.println("visitHomeLinks Exception Message: " + e.getMessage());
        }
    }


}
