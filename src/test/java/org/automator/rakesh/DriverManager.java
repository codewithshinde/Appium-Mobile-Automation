package org.automator.rakesh;

import com.beust.ah.A;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static final ThreadLocal<AndroidDriver> threadDriver = new ThreadLocal<AndroidDriver>();
    public static  final Map<String, UiAutomator2Options> capsMap = new HashMap<>();
    public static final UiAutomator2Options capabilities = new UiAutomator2Options();


    // Private constructor to prevent direct instantiation
    private DriverManager() {
    }

    private static void createDriver(String portNumber) {
        try {
            System.out.println("Creating Driver -----> "+portNumber);
            URL url = new URL("http://127.0.0.1:"+portNumber+"/wd/hub");
            AndroidDriver driver = new AndroidDriver(url, capsMap.get(portNumber));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            threadDriver.set(driver);
        } catch (MalformedURLException e) {
            System.out.println("Failure: "+portNumber+ " :Create Driver Error: "+e.getMessage());

        }
    }

    // Method to get the singleton instance of the driver
    public static AndroidDriver getDriver() {
        return threadDriver.get();
    }

    public static void initiateDriverIfNull(String portNumber) {
        System.out.println("Initiating Driver -----"+portNumber);
        AndroidDriver driver = getDriver();
        if (driver == null) {
            System.out.println("Found Driver -----> Null <------"+portNumber);
            createDriver(portNumber);
            System.out.println("Driver Created -----> FOR <------"+portNumber);
        }
    }

    // Method to close and quit the driver
    public static void quitDriver() {
        System.out.println("----> Quiting Driver <----");
        AndroidDriver driver = getDriver();
        if (driver!= null) {
            driver.quit();
            threadDriver.remove();
        }
    }

    // Method to close and quit the driver
    public static void closeApp(String bundleId) {
        System.out.println("----> Closing the App <----");
        AndroidDriver driver = getDriver();
        if (driver!= null) {
            driver.terminateApp(bundleId);
        }
    }

    // Method to close and quit the driver
    public static void openApp(String bundleId) {
        System.out.println("----> Opening APP <----");
        AndroidDriver driver = getDriver();
        if (driver!= null) {
            driver.activateApp(bundleId);
        }
    }
}
