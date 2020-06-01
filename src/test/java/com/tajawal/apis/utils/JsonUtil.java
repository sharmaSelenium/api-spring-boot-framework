package com.tajawal.apis.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Supplier;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    public  JSONObject             jsonObject;
    private             File                   jsonFile;
    public JSONObject getJSON() { return jsonObject; }

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

    public InputStream readJSONAsInputStream(File file) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            fail("Read JSON As InputStream is failed.");
        }
        return is;
    }

    public InputStream readJSONAsInputStream() {
        InputStream is = null;
        try {
            is = new FileInputStream(jsonFile);
        }
        catch (FileNotFoundException e) {
            fail("Read JSON As InputStream is failed.");
        }
        return is;
    }

    public JSONObject readJSONAsJSONObject() {
        InputStream is = null;
        try {
            is = new FileInputStream(jsonFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener jsonTokener = new JSONTokener(is);
        jsonObject = new JSONObject(jsonTokener);
        return jsonObject;
    }

    public JsonUtil setJSON(String path) {
        jsonFile = new File(System.getProperty("user.dir") + "/src/test/resources/" + path);
        jsonObject = readJSON(jsonFile);
        return this;
    }

    public String modifyJSONKey(File file, String key, String replacement) {
        JSONObject jsonObject = readJSON(file);
        //        jsonObject.getJSONObject("customer").put("id", getTimeStamp());
        String jsonObjectStr = jsonObject.toString();
        jsonObjectStr = jsonObjectStr.replace("\"" + key + "\":", "\"" + replacement + "\":");
        return jsonObjectStr;
    }

    public void jsonComparator(String json1, String json2) {
        JsonParser parser = new JsonParser();
        JsonElement o1 = parser.parse(json1);
        JsonElement o2 = parser.parse(json2);
        assertEquals(o1, o2);
    }

    public JSONObject removeKey(String keyName, String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        jsonObject.remove(keyName);
        return jsonObject;
    }

    public <T> T getObjectFromJson(final File json, final Class<T> type) {
        T obj;
        ObjectMapper mapper = new ObjectMapper();
        try {
            obj = mapper.readValue(json, type);
        } catch (IOException e) {
            obj = null;
            System.out.println(String.format("File: {%s} error: {%s}", json.getPath(), e));
        }
        return obj;
    }

    public <T> List<T> getObjectListFromJson(final File json, final Class<T> type) {
        List<T> obj;
        ObjectMapper mapper = new ObjectMapper();
        try {
            final JavaType valueType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            obj = mapper.readValue(json, valueType);
        } catch (IOException e) {
            obj = null;
            System.out.println(String.format("File: {%s} error: {%s}", json.getPath(), e));
        }
        return obj;
    }

    public String getJsonStringFromPojo(final Object jsonObj) {
        String out;
        ObjectMapper mapper = new ObjectMapper();
        try {
            out = mapper.writeValueAsString(jsonObj);
        } catch (IOException e) {
            System.out.println(String.format("json object: {}, error: {}", jsonObj, e, e));
            out = null;
        }

        return out;
    }



    public void jsonComparatorNew(String json1, String json2) {
        JSONCompareResult result =
            JSONCompare.compareJSON(json1, json2, JSONCompareMode.STRICT);
        System.out.println(result.getMessage());
        System.out.println(" status " +result.passed());}

    public String removeLineByContains(String removedLineContent) {
        String[] lines = getPrettyJSON(readJSONAsJSONObject().toString()).split("\\r?\\n");

        //Filter the line which we want to remove
        for (int i = 0; i < lines.length; i++) {
            if (lines[i]
                .toLowerCase()
                .contains(removedLineContent.toLowerCase())) {
                lines[i] = "";
            }
        }

        //Convert to list to string again
        StringBuilder finalStringBuilder = new StringBuilder();
        for (String s : lines) {
            if (!s.equals("")) {
                finalStringBuilder
                    .append(s)
                    .append(System.getProperty("line.separator"));
            }
        }

        return finalStringBuilder.toString();
    }

    private String getPrettyJSON(String uglyJSONString) {
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;

    }


}
