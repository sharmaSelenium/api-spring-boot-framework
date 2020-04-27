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
public class GoogleType {
    @JsonProperty("TypeNameEN")
    private String typeNameEN;
    @JsonProperty("TypeNameAR")
    private String typeNameAR;
}
