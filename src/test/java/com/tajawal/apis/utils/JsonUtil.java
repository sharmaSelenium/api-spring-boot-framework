package com.tajawal.apis.utils;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    public  JSONObject jsonObject;


    public static JSONObject readJSON(File file) {
        InputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            fail("File not found.");
        }

        JSONTokener jsonTokener = new JSONTokener(is);
        return new JSONObject(jsonTokener);
    }


}
