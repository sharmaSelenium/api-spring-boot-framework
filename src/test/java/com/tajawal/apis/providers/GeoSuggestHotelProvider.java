package com.tajawal.apis.providers;

import com.tajawal.apis.dto.geoSuggestHotels.GeoSuggestHotelResponse;
import com.tajawal.apis.dto.geoSuggestHotels.Hotel;
import com.tajawal.apis.dto.geoSuggestHotels.Location;
import io.restassured.response.Response;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GeoSuggestHotelProvider {
    private GeoSuggestHotelResponse geoSuggestHotelResponse;

    public void setGeoSuggestHotelResponse(Response response){
        geoSuggestHotelResponse = response.getBody().as(GeoSuggestHotelResponse.class);
    }
    public List<Hotel> getHotels(){
        return geoSuggestHotelResponse.getHotels();
    }

    public List<Location> getLocations(){
        return geoSuggestHotelResponse.getLocations();
    }
}
