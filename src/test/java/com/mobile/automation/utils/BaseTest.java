package com.mobile.automation.utils;


import com.mobile.automation.config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static com.mobile.automation.utils.Constants.ANDROID_APP_PATH;
import static com.mobile.automation.utils.Constants.IOS_APP_PATH;


@ContextConfiguration(classes = Config.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource({"classpath:config.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BaseTest extends BaseDriver {

        @BeforeAll
        public void launchDriver(){
            startServer();
            setAppPath();
            setDriverType();
            setDriverTimeOut();
        }

        @After
        public void quitDriver(){
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        }

        @AfterAll
        public void stopServer(){
            if (server != null) {
                server.stop();
            }
        }


        private void setDriverType() {
            if(config.getAppType.get().equalsIgnoreCase("androidnative")){
                capabilities = new DesiredCapabilities();
                driver = new AndroidDriver<>(url, getAndroidNativeCapabilities(capabilities));
            }
            else if(config.getAppType.get().equalsIgnoreCase("iosnative")){
                // ios driver
            }

        }


        private DesiredCapabilities getAndroidNativeCapabilities(DesiredCapabilities capabilities){
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getAndroidVersion.get());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.APP, Paths.get(ANDROID_APP_PATH).toAbsolutePath().toString());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getAndroidDevice.get());
            return capabilities;
        }

        private DesiredCapabilities getIosNativeCapabilities(DesiredCapabilities capabilities){
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getIosVersion.get());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
            capabilities.setCapability(MobileCapabilityType.APP, Paths.get(IOS_APP_PATH));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getIosDevice.get());
            return capabilities;
        }


        private void setAppPath()  {
            if(config.getExecutionMode.get().equalsIgnoreCase("local")){
                try {
                    url = new URL(config.getURL.get() + "/wd/hub");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if(config.getExecutionMode.get().equalsIgnoreCase("remote")){
                // future implementation
            }
        }

    private void setDriverTimeOut() {
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getDriverTimeOut.get()), TimeUnit.SECONDS);
    }

    private AppiumDriverLocalService startServer(){

        if(!checkIfServerIsRunning(config.getPort.get())){
            server = AppiumDriverLocalService.buildDefaultService();
            server.start();
        }
        return server;
    }


    public static boolean checkIfServerIsRunning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
