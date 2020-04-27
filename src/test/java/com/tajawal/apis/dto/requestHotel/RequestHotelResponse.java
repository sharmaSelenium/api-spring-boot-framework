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
public class RequestHotelResponse {
    @JsonProperty("type")
    private String type;
    @JsonProperty("query")
    private String query;
    @JsonProperty("queryParameters")
    private String queryParameters;
    @JsonProperty("queryParametersObj")
    private QueryParametersObj queryParametersObj;
    @JsonProperty("isCountry")
    private Boolean isCountry;
    @JsonProperty("detail")
    private Detail detail;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("title")
    private String title;
}
