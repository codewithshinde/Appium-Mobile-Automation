package org.automator.rakesh;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.automater.setup.AppEventsObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    AndroidDriver driver;
    public PageBase(AndroidDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks on a mobile element.
     *
     * @param elementBy Locator for the element
     * clickElement(AppiumBy.accessibilityId("login-button"), "Login Button");
     */
    public void clickElement(By elementBy, String strDescription) {
        try {
            waitVisibility(elementBy, strDescription);
            driver.findElement(elementBy).click();
            //Log.info("Clicked on element : " + strDescription + " using locator: " + elementBy + " ");

        } catch (NoSuchElementException e) {
            //Log.error("Error clicking " + strDescription + " : " + e.getMessage());
            //takeScreenshot(strDescription + "_not_clickable");
            throw e;
        } catch (Exception e) {
            //Log.error("Error clicking " + strDescription + " : " + e.getMessage() + " " + e);
            throw e;
        }

    }

    /**
     * Waits for the given element to be visible on the page.
     *
     * @param elementBy      the locator for the element
     * @param strDescription a description of the element, for logging
     * @param intWaitSeconds the number of seconds to wait for the element to be visible
     * @example waitForElementVisibility(By.xpath ( " submit button "), "Submit button", 10);
     */
    private void waitForElementVisibility(By elementBy, String strDescription, int intWaitSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(intWaitSeconds));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
        } catch (TimeoutException e) {

            throw e;

        } catch (NoSuchElementException e) {
            //Log.error(strDescription + " not found while waiting" + e.getMessage());
            throw e;

        } catch (Exception e) {
            //Log.error("Error waiting for " + strDescription + " to be visible " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible.
     *
     * @param elementBy      Element locator.
     * @param strDescription Element name for logging.
     */
    public void waitVisibility(By elementBy, String strDescription) {
        try {
            waitForElementVisibility(elementBy, strDescription, 10);
        } catch (TimeoutException e) {
            //Log.error("Timeout waiting for " + strDescription + " to be visible " + e.getMessage());

            throw e;

        } catch (NoSuchElementException e) {
            //Log.error(strDescription + " not found while waiting" + e.getMessage());
            throw e;

        } catch (Exception e) {
            //Log.error("Error waiting for " + strDescription + " to be visible " + e.getMessage());
            throw e;
        }
    }


    public void writeTextToElement(By elementBy, String strText, String strDescription, Boolean blnIsHideKeyboard) {
        try {
            waitVisibility(elementBy, strDescription);
            driver.findElement(elementBy).click();
            driver.findElement(elementBy).clear();
            driver.findElement(elementBy).sendKeys(strText);

            if (driver.isKeyboardShown() && blnIsHideKeyboard) {
                disableImplicitWait(5);
                try {
                    driver.findElement(AppiumBy.accessibilityId("Return")).click();
                    //Log.info("Dismissed the keyboard by pressing the return key");
                } catch (NoSuchElementException e1) {
                    try {
                        driver.findElement(AppiumBy.accessibilityId("Done")).click();
                        //Log.info("Dismissed the keyboard by pressing the done key");
                    } catch (NoSuchElementException e2) {
                        //Log.error("Unable to dismiss the keyboard by pressing the Return or Done key");
                    }
                }
                enableImplicitWait();
            }
            //Log.info("Entered text '" + strText + "' into element: " + strDescription + " using locator:  (" + elementBy + ")");
        } catch (NoSuchElementException e) {
            //Log.error(strDescription + " Element is not found or visible to send keys to an element using locator: " + elementBy + " : " + e.getMessage());
            //takeScreenshot(strDescription + "_not_clickable");
            throw e;
        } catch (Exception e) {
            //Log.error("Error clicking " + strDescription + " : " + e.getMessage() + " " + e);
            throw e;
        }
    }


    /**
     * Pause execution for given number of seconds.
     *
     * @param seconds Number of seconds to pause.
     */
    public static void pause(double seconds) {
        //Log.info("Waiting for " + seconds + " seconds...");
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Log.info("Finished waiting.");
    }

    /**
     * Disable implicit wait.
     *
     * @param intSeconds Number of seconds to wait before timing out
     * example disableImplicitWait(10);
     */
    public void disableImplicitWait(int intSeconds) {
        try {
            //Log.info("Disabling implicit wait for " + intSeconds + " seconds");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(intSeconds));
            //Log.info("Implicit wait disabled for " + intSeconds + " seconds");
        } catch (TimeoutException e) {
            //Log.error("Timeout waiting for disabling implicit wait:" + e.getMessage());
            throw e;

        } catch (Exception e) {
            //Log.error("Timeout waiting for disabling implicit wait:" + e.getMessage());
            throw e;
        }
    }


    /**
     * Enable implicit wait to dynamically wait for elements
     *
     * example enableImplicitWait();
     */
    public void enableImplicitWait() {
        try {
            //Log.info("Enabling implicit wait for" + Duration.ofSeconds(GLOBAL_WAIT_SECONDS));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            //Log.info("Implicit wait enabled for " + Duration.ofSeconds(GLOBAL_WAIT_SECONDS));
        } catch (TimeoutException e) {
            //Log.error("Timeout waiting for enabling implicit wait:" + e.getMessage());
            throw e;

        } catch (Exception e) {
            //Log.error("Timeout waiting for enabling implicit wait:" + e.getMessage());
            throw e;
        }
    }
}
