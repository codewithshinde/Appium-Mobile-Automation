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
        return threadDriver.get();
    }
    private void setDriver(AndroidDriver driver) {
        threadDriver.set(driver);
    }

    //NOTE: Setup allure @Step("Set up Appium server and launch {0} app")
    @BeforeMethod
    @Parameters({"deviceName", "platformVersion", "uuid", "appPackage", "appActivity", "portNumber", "strAppPath"})
    public void initializeDriver(String deviceName, String platformVersion, String uuid, String appPackage, String appActivity, String portNumber, String strAppPath) {
        try {
            //startService(portNumber);
            UiAutomator2Options capabilities = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setPlatformVersion(platformVersion)
                    .setDeviceName(deviceName)
                    .setUdid(uuid)
                    .setAppPackage(appPackage)
                    .setAppActivity(appActivity)
                    .setApp(strAppPath);
            URL url = new URL("http://127.0.0.1:"+portNumber+"/wd/hub");
            AndroidDriver androidDriver = new AndroidDriver(url, capabilities);
            androidDriver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(5));
            setDriver(androidDriver);
            //setupDriverTimeouts();
        } catch (Exception ex) {
            System.out.println("initializeDriver Cause is: " + ex.getCause());
            System.out.println("initializeDriver Message: " + ex.getMessage());
        }

    }

    @BeforeClass
    @Parameters({"uuid", "strAppPath"})
    public void beforeClass(String uuid, String strAppPath) {
        System.out.println("Before Class invoked");
        AndroidDriver driver = getDriver();
        if (!isFirstLaunch) {
            //Log.info("Checking app launch context: " + context);
            if (driver != null) {
                String context = driver.getContext();
                if (context != null && context.equals("NATIVE_APP")) {
                    driver.terminateApp(uuid);
                } else {
                    //Log.info("Installing app: " + strBundleId);
                    driver.installApp(strAppPath);
                    //Log.info("App installed using: " + strAppPath);
                }
                driver.activateApp(uuid);
                //Log.info("App activated using: " + strBundleId);
            }
        } else {
            isFirstLaunch = false;
        }
    }

    //@Step("Terminate app using strBundleId: {0}")
    @Parameters({"uuid"})
    @AfterClass
    public void afterClass(String uuid) {
        System.out.println("After Class invoked");
        AndroidDriver driver = getDriver();
        try {
            if(driver != null) {
                String context = driver.getContext();
                if ( context != null && context.equals("NATIVE_APP")) {
                    driver.terminateApp(uuid);
                    PageBase.pause(5);
                }
            }
        } catch (Exception e) {
            //Log.error("Error terminating app: " + e.getMessage());
            throw new RuntimeException("App termination failed", e);
        }
    }

    @AfterMethod
    public void closeDriver() {
        AndroidDriver driver = getDriver();
        if (null != driver) {
            driver.quit();
            threadDriver.remove();
        }
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
