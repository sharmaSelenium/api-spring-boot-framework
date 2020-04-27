package com.tajawal.apis.data.geosuggesthotels;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueryHotelSearch {

    VALID_CITY("query","paris"),
    VALID_COUNTRY("query","india"),
    INVALID_CITY_NAME("query","abcsdsdb"),
    INVALID_COUNTRY_NAME("query","abdhkcxs"),
    EMPTY_HOTEL("query","");

    private String key;
    private String value;

}