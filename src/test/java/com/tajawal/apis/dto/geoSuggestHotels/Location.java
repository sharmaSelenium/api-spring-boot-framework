package com.tajawal.apis.dto.geoSuggestHotels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @JsonProperty("name")
    private String name;
    @JsonProperty("placeId")
    private String placeId;
    @JsonProperty("source")
    private String source;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonProperty("displayType")
    private String displayType;
    @JsonProperty("googleType")
    private GoogleType googleType;
}
