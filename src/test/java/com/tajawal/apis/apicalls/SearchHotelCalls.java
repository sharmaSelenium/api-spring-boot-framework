package com.tajawal.apis.apicalls;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.springframework.stereotype.Component;

@Component
public class SearchHotelCalls extends BaseCall{

    public Response getHotelByGeoSuggestion(Headers headers , String endpoint){ return restUtil.get(headers,endpoint); }

    public Response requestHotel(String data, String endpoint, Headers headers){
        return restUtil.post(data,endpoint,headers);
    }
}
