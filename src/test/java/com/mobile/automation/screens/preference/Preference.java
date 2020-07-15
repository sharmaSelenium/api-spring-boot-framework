package com.mobile.automation.screens.preference;

import com.mobile.automation.screens.HomeScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static com.mobile.automation.utils.Wrappers.waitUntilClickable;
import static com.mobile.automation.utils.Wrappers.waitUntilVisible;

public class Preference extends HomeScreen {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
    private MobileElement prefDependencies;

    @AndroidFindBy(id = "android:id/checkbox")
    private MobileElement wifiCheckBox;

    public Preference(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void selectPreferenceDependencies(){
       waitUntilVisible(prefDependencies ,10);
        prefDependencies.click();
    }

    public void selectWifiCheckBox(){
        waitUntilVisible(wifiCheckBox ,10);
        wifiCheckBox.click();
    }
}
