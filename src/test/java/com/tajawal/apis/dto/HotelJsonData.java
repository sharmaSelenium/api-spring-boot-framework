package com.tajawal.apis.dto;

import static com.tajawal.apis.utils.JsonUtil.readJSON;

import java.io.File;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class HotelJsonData {
    private JSONObject jsonObject;
    private static File hotelFile = new File(System.getProperty("user.dir") +
        "/src/test/resources/payloads/searchHotel.json");

    public JSONObject loadHotelJsonData(String checkInDate , String checkOutDate ) {
        jsonObject = readJSON(hotelFile);
        jsonObject.getJSONObject("dates").put("checkin" ,checkInDate);
        jsonObject.getJSONObject("dates").put("checkout" ,checkOutDate);
        return jsonObject;
    }
}

