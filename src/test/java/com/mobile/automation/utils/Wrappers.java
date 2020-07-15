package com.mobile.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class Wrappers extends BaseDriver {

    public static void waitUntilVisible(MobileElement element, int timeOutInSec) {
        int waitTime = getImplicitWaitTimeInSec(timeOutInSec);
        try {
            FluentWait<AppiumDriver<MobileElement>> fWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(waitTime))
                    .pollingEvery(Duration.ofMillis(50))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            fWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail("Expected element not displayed in " + waitTime + " seconds");
        }
    }


    public static void waitForSomeTime(int timeOutInSec) {
        try {
            Thread.sleep(timeOutInSec * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    public static void waitUntilClickable(MobileElement element, int timeOutInSec) {
        int waitTime = getImplicitWaitTimeInSec(timeOutInSec);
        try {
            FluentWait<AppiumDriver<MobileElement>> fWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(waitTime))
                    .pollingEvery(Duration.ofMillis(50))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            fWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("Expected element not clickable in " + waitTime + " seconds");
        }
    }


    private static int getImplicitWaitTimeInSec(int timeOutInSec) {
        int waitTime;
        if (timeOutInSec == 0) {
            waitTime = Integer.parseInt(System.getProperty("driverTimeout"));
        } else {
            waitTime = timeOutInSec;
        }
        return waitTime;
    }

    public static boolean ifElementPresent(MobileElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
