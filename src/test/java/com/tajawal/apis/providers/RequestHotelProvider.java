package com.tajawal.apis.providers;

import com.tajawal.apis.dto.requestHotel.RequestHotelResponse;
import io.restassured.response.Response;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RequestHotelProvider {
    private RequestHotelResponse requestHotelResponse;

    public void setRequestHotelResponse(Response response) {
        requestHotelResponse = response.getBody().as(RequestHotelResponse.class);
    }

    public String getTitle() {
        return requestHotelResponse.getTitle();
    }

    public int getStatus() {
        return requestHotelResponse.getStatus();
    }

    public List<String> getCheckInDetails() {
        return requestHotelResponse.getDetail().getDatesCheckin();
    }

    public List<String> getCheckOutDetails() {
        return requestHotelResponse.getDetail().getDatesCheckout();
    }

    public List<String> getDestinationDetails() {
        return requestHotelResponse.getDetail().getDestination();
    }

    public String getQuery() {
        return requestHotelResponse.getQuery();
    }

    public String getType() {
        return requestHotelResponse.getType();
    }
}
