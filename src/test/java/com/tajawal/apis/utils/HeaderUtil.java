package com.tajawal.apis.utils;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class HeaderUtil {
    public ArrayList<Header> defaultHeaders = new ArrayList<>();
    public Headers headers;
    public Headers getHeaders() {
        return headers;
    }

    public HeaderUtil setDefaultHeaders() {
        if (defaultHeaders == null || defaultHeaders.isEmpty()) {
            defaultHeaders.add(new Header("Content-Type", "application/json"));
        }
        headers = new Headers(defaultHeaders);
        return this;
    }

    public HeaderUtil clearHeaders() {
        defaultHeaders.clear();
        headers = new Headers(defaultHeaders);
        return this;
    }
}