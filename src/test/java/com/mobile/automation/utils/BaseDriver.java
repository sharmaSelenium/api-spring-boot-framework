package com.mobile.automation.utils;

import com.mobile.automation.commons.Configurations;
import com.mobile.automation.screens.HomeScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;


@Getter
@Setter
@RequiredArgsConstructor
public class BaseDriver {

    @Autowired
    Configurations config;

    public static AppiumDriver<MobileElement> driver;

    public static DesiredCapabilities capabilities = null;

    public static URL url = null;

    public static AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public static AppiumDriverLocalService server ;


}
