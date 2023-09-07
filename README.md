# Mobile Automation Project

## Overview

This project is aimed at automating mobile application testing using Appium and TestNG. It includes test cases for the "com.wdiodemoapp" mobile app, covering login and signup functionalities.

## Prerequisites

Before running the tests, make sure you have the following prerequisites installed:

- [Appium](https://appium.io/)
- [Android Emulator](https://developer.android.com/studio/run/emulator)
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [TestNG](https://testng.org/doc/)

## Project Structure

The project is structured as follows:

- `src/test/java`: Contains test classes for different test scenarios.
- `src/test/resources`: Contains the mobile app APK file and any other test resources.

## Running the Tests

To run the tests, follow these steps:

1. Start your Android emulator(s) (e.g., Pixel7 and Pixel7-2).

2. Update the test configuration in the testng.xml file to match the device settings, including `deviceName`, `platformVersion`, `uuid`, `appPackage`, `appActivity`, `portNumber`, and `strAppPath`.

3. Execute the tests using the following command:


This command will execute the tests specified in the testng.xml file in parallel on the configured devices.

### Running Tests in Parallel

To run tests in parallel, we utilize TestNG's parallel execution feature. In the testng.xml file, you can configure multiple test suites, each targeting different devices, as shown below:

```xml
<suite name="Automator Test Suite" parallel="tests">
 <listeners>
     <listener class-name="org.automator.rakesh.TestListener"/>
 </listeners>
 <test name="Pixel7 Test" thread-count="4">
     <!-- Configuration for Pixel7 -->
 </test>
 <test name="Pixel7-2 Test" thread-count="4">
     <!-- Configuration for Pixel7-2 -->
 </test>
</suite>

