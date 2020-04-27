package com.tajawal.apis.dto.requestHotel;

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
public class QueryParametersObj {
    @JsonProperty("sortBy")
    private String sortBy;
    @JsonProperty("isGeo")
    private Integer isGeo;
    @JsonProperty("hId")
    private String hId;
    @JsonProperty("isCountry")
    private String isCountry;
    @JsonProperty("placeId")
    private String placeId;
    @JsonProperty("types")
    private List<Object> types = null;
}
