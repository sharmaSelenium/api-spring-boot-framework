package com.mobile.automation.commons;

import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Configurations {
    @Value("${aut}")
    private String aut;
    @Value("${url}")
    private String url;
    @Value("${androidDevice}")
    private String androidDevice;
    @Value("${iosDevice}")
    private String iosDevice;
    @Value("${androidPlatformVersion}")
    private String androidPlatformVersion;
    @Value("${executionType}")
    private String executionType;
    @Value("${iosPlatformVersion}")
    private String iosPlatformVersion;
    @Value("${driverTimeOut}")
    private String driverTimeOut;
    @Value("${port}")
    private String port;


    public Supplier<String> getAppType = () -> aut;
    public Supplier<String> getAndroidDevice = () -> androidDevice;
    public Supplier<String> getIosDevice = () -> iosDevice;
    public Supplier<String> getURL = () -> url;
    public Supplier<String> getAndroidVersion = () -> androidPlatformVersion;
    public Supplier<String> getExecutionMode = () -> executionType;
    public Supplier<String> getIosVersion = () -> iosPlatformVersion;
    public Supplier<String> getDriverTimeOut = () -> driverTimeOut;
    public Supplier<Integer> getPort = () -> Integer.valueOf(port);
}
