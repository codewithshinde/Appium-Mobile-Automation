package org.automator.rakesh;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class TestBase {
    private final ThreadLocal<AndroidDriver> threadDriver = new ThreadLocal<AndroidDriver>();
    private static boolean isFirstLaunch = true;

    public AndroidDriver getDriver() {
        return DriverManager.getDriver();
    }
    private void setDriver(AndroidDriver driver) {
        threadDriver.set(driver);
    }


    @BeforeTest
    @Parameters({"deviceName", "platformVersion", "uuid", "appPackage", "appActivity", "portNumber", "strAppPath"})
    public void beforeTestMethod(String deviceName, String platformVersion, String uuid, String appPackage, String appActivity, String portNumber, String strAppPath) {
        System.out.println("Before Test>> "+deviceName);
        UiAutomator2Options capabilities = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion(platformVersion)
                .setDeviceName(deviceName)
                .setUdid(uuid)
                .setAppPackage(appPackage)
                .setAppActivity(appActivity)
                .setApp(strAppPath);
        DriverManager.capsMap.put(portNumber, capabilities);
        DriverManager.initiateDriverIfNull(portNumber);
    }

    //NOTE: Setup allure @Step("Set up Appium server and launch {0} app")
    @BeforeMethod
    @Parameters({"deviceName", "platformVersion", "uuid", "appPackage", "appActivity", "portNumber", "strAppPath"})
    public void initializeDriver(String deviceName, String platformVersion, String uuid, String appPackage, String appActivity, String portNumber, String strAppPath) {
        try {
            //startService(portNumber);
            System.out.println("Before Method>> "+deviceName);
            UiAutomator2Options caps = DriverManager.capsMap.get(portNumber);
            if(null != caps) {
                System.out.println("Before Method Caps of>> "+caps.getDeviceName());
                AndroidDriver dr = DriverManager.getDriver();
                if(dr != null) {
                    DriverManager.openApp(appPackage);
                    System.out.println("Driver of>> "+dr.getCapabilities().getCapability("deviceName"));
                } else {
                    System.out.println("-------- END --------Driver is NULL>> ");
                }
            } else {
                System.out.println("Before Method Caps is NULL");
            }

        } catch (Exception ex) {
            System.out.println("initializeDriver Cause is: " + ex.getCause());
            System.out.println("initializeDriver Message: " + ex.getMessage());
        }
    }

    @BeforeClass
    @Parameters({"appPackage", "strAppPath"})
    public void beforeClass(String appPackage, String strAppPath) {
        System.out.println("Before Class invoked");
        AndroidDriver driver = getDriver();
        if (!isFirstLaunch) {
            //Log.info("Checking app launch context: " + context);
            if (driver != null) {
                String context = driver.getContext();
                if (context != null && context.equals("NATIVE_APP")) {
                    driver.terminateApp(appPackage);
                } else {
                    //Log.info("Installing app: " + strBundleId);
                    driver.installApp(strAppPath);
                    //Log.info("App installed using: " + strAppPath);
                }
                driver.activateApp(appPackage);
                //Log.info("App activated using: " + strBundleId);
           }
        } else {
            isFirstLaunch = false;
        }
    }

    //@Step("Terminate app using strBundleId: {0}")
    @Parameters({"appPackage"})
    @AfterClass
    public void afterClass(String appPackage) {
        System.out.println("After Class invoked");
        AndroidDriver driver = getDriver();
        try {
            if(driver != null) {
                String context = driver.getContext();
                if ( context != null && context.equals("NATIVE_APP")) {
                    driver.terminateApp(appPackage);
                    PageBase.pause(5);
                }
            }
        } catch (Exception e) {
            //Log.error("Error terminating app: " + e.getMessage());
            throw new RuntimeException("App termination failed", e);
        }
    }

    @Parameters({"appPackage"})
    @AfterMethod
    public void initiateAfterMethod(String appPackage){
        System.out.println("----> After Method <----");
        DriverManager.closeApp(appPackage);
    }

    public void startService(String portNumber) {
        try {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1")
                    .usingPort(Integer.parseInt(portNumber))
                    .withArgument (GeneralServerFlag.BASEPATH, "/wd/hub")
                    .withAppiumJS(new File("C:\\Users\\karthik\\AppData\\Roaming\\nvm\\v18.10.0\\node_modules\\appium\\build\\lib\\main.js"))
                    .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
            service.start();
            System.out.println("Service Started for Port: " +portNumber);
        } catch(Exception e) {
            System.out.println("startService Cause is: " + e.getCause());
            System.out.println("startService Message: " + e.getMessage());
        }

    }

    private void setupDriverTimeouts () {
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds (30));
    }

}