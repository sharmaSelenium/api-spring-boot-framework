package com.mobile.automation.tests;

import com.mobile.automation.screens.HomeScreen;
import com.mobile.automation.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Epic("Test epic")
@Feature("Test Feature")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class TestDemoApp extends BaseTest {

    @Test
    public void test(){
        System.out.println("test started");
        HomeScreen homeScreen = new HomeScreen(driver);
        homeScreen.selectPreference();
        homeScreen.preference().selectPreferenceDependencies();
        homeScreen.preference().selectWifiCheckBox();

    }

}
