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
public class Hotel {
    @JsonProperty("hotelId")
    private Integer hotelId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    @JsonProperty("displayType")
    private String displayType;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("isActive")
    private Integer isActive;
}
