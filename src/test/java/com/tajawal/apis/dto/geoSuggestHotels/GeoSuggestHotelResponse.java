package com.tajawal.apis.dto.geoSuggestHotels;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GeoSuggestHotelResponse {
    @JsonProperty("hotels")
    private List<Hotel> hotels = null;
    @JsonProperty("locations")
    private List<Location> locations = null;
}
