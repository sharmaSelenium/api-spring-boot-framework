package com.tajawal.apis.data.requesthotels;

import static com.tajawal.apis.utils.JsonUtil.readJSON;

import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class HotelSearchRequestData {
    private JSONObject jsonObject;
    private static File hotelRequestFile = new File(System.getProperty("user.dir") +
        "/src/test/resources/payloads/searchHotel.json");

    public JSONObject getHotelSearchRequestData() {
        jsonObject = readJSON(hotelRequestFile);

        return jsonObject;
    }

}
