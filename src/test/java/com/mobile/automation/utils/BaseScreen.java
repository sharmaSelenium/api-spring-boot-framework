package com.mobile.automation.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseScreen extends BaseDriver{

    public BaseScreen(AndroidDriver driver) {
        PageFactory.initElements(driver, this);
        BaseDriver.driver = driver;
    }

}
