package org.automater.setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.time.Duration;

public class BaseSetup {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return BaseSetup.driver.get();
    }
    public void setDriver(AppiumDriver driver) {
        BaseSetup.driver.set(driver);
    }

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion", "uuid", "appPackage", "appActivity", "portNumber"})
    public void initializeDriver(String deviceName, String platformVersion, String uuid, String appPackage, String appActivity, String portNumber) {
        try {
            startService(portNumber);
            UiAutomator2Options capabilities = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setPlatformVersion(platformVersion)
                    .setDeviceName(deviceName)
                    .setUdid(uuid)
                    .setAppPackage(appPackage)
                    .setAppActivity(appActivity);
            URL url = new URL("http://127.0.0.1:"+portNumber+"/wd/hub");
            AndroidDriver androidDriver = new AndroidDriver(url, capabilities);
            setDriver(androidDriver);
            setupDriverTimeouts();
        } catch (Exception ex) {
            System.out.println("initializeDriver Cause is: " + ex.getCause());
            System.out.println("initializeDriver Message: " + ex.getMessage());
        }

    }

    @AfterMethod
    public void closeDriver() {
        if (null != BaseSetup.getDriver()) {
            getDriver().quit();
            driver.remove();
        }
    }

    public void startService(String portNumber) {
        try {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1")
                    .usingPort(Integer.parseInt(portNumber))
                    .withArgument (GeneralServerFlag.BASEPATH, "/wd/hub");
            AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
            service.start();
            System.out.println("Service Started for Port: " +portNumber);
        } catch(Exception e) {
            System.out.println("startService Cause is: " + e.getCause());
            System.out.println("startService Message: " + e.getMessage());
        }

    }

    private static void setupDriverTimeouts () {
        getDriver().manage().timeouts()
                .implicitlyWait (Duration.ofSeconds (30));
    }

}
