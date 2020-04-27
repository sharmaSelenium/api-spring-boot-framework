package com.tajawal.apis.tests.searchhotels;

import static com.tajawal.apis.data.geosuggesthotels.DisplayType.Hotel;
import static com.tajawal.apis.data.geosuggesthotels.DisplayType.Location;
import static com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch.EMPTY_HOTEL;
import static com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch.INVALID_CITY_NAME;
import static com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch.INVALID_COUNTRY_NAME;
import static com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch.VALID_CITY;
import static com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch.VALID_COUNTRY;
import static com.tajawal.apis.data.requesthotels.RoomGuestsMapping.EMPTY_DESTINATION;
import static com.tajawal.apis.data.requesthotels.RoomGuestsMapping.ONE_ROOM_2_ADT_0_CHD_PARIS;
import static com.tajawal.apis.data.requesthotels.RoomGuestsMapping.ONE_ROOM_2_ADT_1_CHD_PARIS;
import static com.tajawal.apis.data.requesthotels.RoomGuestsMapping.THREE_ROOMS_1_ADT_1_CHD_2_ADT_2_CHD_1_ADT;
import static com.tajawal.apis.data.requesthotels.RoomGuestsMapping.TWO_ROOMS_2_ADT_2_CHD_2_ADT_1_CHD_PARIS;
import static com.tajawal.apis.expectations.GatewayErrorExpectations.BAD_REQUEST_CHECK_IN_DATE_BEFORE_CHECK_OUT;
import static com.tajawal.apis.expectations.GatewayErrorExpectations.BAD_REQUEST_CHECK_IN_INVALID_DATE;
import static com.tajawal.apis.expectations.GatewayErrorExpectations.BAD_REQUEST_CHECK_OUT_INVALID_DATE;
import static com.tajawal.apis.expectations.GatewayErrorExpectations.BAD_REQUEST_EMPTY_DESTINATION;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Epic("Search Hotels System Integration Tests")
@Feature("Search Hotels System Integration Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SitServiceTest extends BaseTest {


    @AfterEach
    void teardown() {
        headerUtil.clearHeaders();
        queryParamUtil.clearQueryParams();
    }

    /**
     * Scenario 1 : Hotel Search By Geo-Suggestion Service.
     **/

    @Test
    @DisplayName("Search hotel by valid city name")
    void testSearchHotelByValidCityName() {
        searchHotelSteps.
            GivenISearchHotel(VALID_CITY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved();
    }

    @Test
    @DisplayName("Search hotel by valid city name and verify city is displayed in all hotels ")
    void testSearchAndVerifyCityInHotelList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_CITY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyCityInAllHotels(VALID_CITY);
    }

    @Test
    @DisplayName("Search hotel by valid country name and verify country is displayed in all hotels ")
    void testSearchAndVerifyCountryInHotelList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_COUNTRY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyCountryInLocations(VALID_COUNTRY);
    }

    @Test
    @DisplayName("Search hotel by valid city name and verify display type in hotel list")
    void testSearchByCityAndVerifyDisplayTypeInHotelList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_CITY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyDisplayTypeInHotelList(Hotel);
    }

    @Test
    @DisplayName("Search hotel by valid country name and verify display type in location list ")
    void testSearchByCountryAndVerifyDisplayTypeInLocationList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_COUNTRY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyDisplayTypeInLocationList(Location);
    }

    @Test
    @DisplayName("Search hotel by valid city name and get all hotels in the list ")
    void testSearchByCityAndGetHotelNamesList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_CITY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIGetHotelNamesInTheList();
    }

    @Test
    @DisplayName("Search hotel by valid country name and get all hotels in the list ")
    void testSearchByCountryAndGetHotelNamesList() {
        searchHotelSteps.
            GivenISearchHotel(VALID_COUNTRY).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIGetHotelNamesInTheList();
    }

    @Test
    @DisplayName("Search hotel by invalid city name and verify empty list ")
    void testSearchByInValidCityAndVerifyEmptyList() {
        searchHotelSteps.
            GivenISearchHotel(INVALID_CITY_NAME).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyEmptyHotelAndLocationList();
    }

    @Test
    @DisplayName("Search hotel by invalid country name and verify empty list ")
    void testSearchByInValidCountryAndVerifyEmptyList() {
        searchHotelSteps.
            GivenISearchHotel(INVALID_COUNTRY_NAME).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyEmptyHotelAndLocationList();
    }

    @Test
    @DisplayName("Search hotel by empty query and verify empty list ")
    void testSearchByEmptyQueryAndVerifyEmptyList() {
        searchHotelSteps.
            GivenISearchHotel(EMPTY_HOTEL).
            AndIRetrieveHotelsByGeoSuggestion().
            ThenISeeHotelListIsRetrieved().
            AndIVerifyEmptyHotelAndLocationList();
    }

    /**
     * Scenario 2 : Hotel Request Service
     **/
    @Test
    @DisplayName(" Search Hotel in valid destination in future days with 1 room,2 adults and 1 child ")
    void testSearchRoomFor1Room2Adt1Chd() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_1_CHD_PARIS, 2 ,3).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful();
    }

    @Test
    @DisplayName(" Search Hotel in valid destination in future days and verify type")
    void testSearchRoomAndVerifyType() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_1_CHD_PARIS, 2 ,3).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyTypeInHotelSearch(Hotel);
    }

    @Test
    @DisplayName(" Search Hotel in valid destination in future days and verify destination")
    void testSearchRoomAndVerifyDestination() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_1_CHD_PARIS, 2 ,3).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyDestinationInQuery(ONE_ROOM_2_ADT_1_CHD_PARIS);
    }

    @Test
    @DisplayName(" Search Hotel in valid destination in future days and verify dates")
    void testSearchRoomAndVerifyDates() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_1_CHD_PARIS, 2 ,3).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyDatesInQuery();
    }

    @Test
    @DisplayName("Search Hotel in future days with 1 room,2 adults and 0 child and verify response")
    void testSearchRoomFor1Room2AdtAndVerifyResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(ONE_ROOM_2_ADT_0_CHD_PARIS, 2 ,3).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyDestinationInQuery(ONE_ROOM_2_ADT_0_CHD_PARIS).
            AndIVerifyDatesInQuery();
    }

    @Test
    @DisplayName("Search Hotel in future days with 2 rooms and verify response")
    void testSearchRoomFor2RoomsAndVerifyResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(TWO_ROOMS_2_ADT_2_CHD_2_ADT_1_CHD_PARIS,
                22 ,24).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyDestinationInQuery(TWO_ROOMS_2_ADT_2_CHD_2_ADT_1_CHD_PARIS).
            AndIVerifyDatesInQuery();
    }

    @Test
    @DisplayName("Search Hotel in future days with 3 rooms and verify response")
    void testSearchRoomFor3RoomsAndVerifyResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(THREE_ROOMS_1_ADT_1_CHD_2_ADT_2_CHD_1_ADT,
                15 ,23).
            AndISearchForHotel().
            ThenISeeSearchRequestSuccessful().
            AndIVerifyDestinationInQuery(THREE_ROOMS_1_ADT_1_CHD_2_ADT_2_CHD_1_ADT).
            AndIVerifyDatesInQuery();
    }

    @Test
    @DisplayName("Search Hotel with empty destination and verify error response")
    void testSearchHotelWithEmptyDestinationAndVerifyErrorResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(EMPTY_DESTINATION, 15 ,23).
            AndISearchForHotel().
            ThenIVerifyGatewayBadRequest(BAD_REQUEST_EMPTY_DESTINATION);
    }

    @Test
    @DisplayName("Search Hotel with check in date more than check out date and verify error response")
    void testSearchHotelWithGreaterCheckInDateAndVerifyErrorResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload(THREE_ROOMS_1_ADT_1_CHD_2_ADT_2_CHD_1_ADT, 5 ,3).
            AndISearchForHotel().
            ThenIVerifyGatewayBadRequest(BAD_REQUEST_CHECK_IN_DATE_BEFORE_CHECK_OUT);
    }

    @Test
    @DisplayName("Search Hotel with wrong check in date  and verify error response")
    void testSearchHotelWithWrongCheckInDateAndVerifyErrorResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload("23-344-33", "12-08-2020").
            AndISearchForHotel().
            ThenIVerifyGatewayBadRequest(BAD_REQUEST_CHECK_IN_INVALID_DATE);
    }

    @Test
    @DisplayName("Search Hotel with wrong check out date  and verify error response")
    void testSearchHotelWithWrongCheckOutDateAndVerifyErrorResponse() {
        searchHotelSteps.
            GivenIGenerateSearchHotelPayload("12-08-2020", "362782").
            AndISearchForHotel().
            ThenIVerifyGatewayBadRequest(BAD_REQUEST_CHECK_OUT_INVALID_DATE);
    }


}