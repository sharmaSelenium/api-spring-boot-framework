package com.tajawal.apis.steps;

import com.tajawal.apis.data.geosuggesthotels.DisplayType;
import com.tajawal.apis.data.geosuggesthotels.QueryHotelSearch;
import com.tajawal.apis.data.requesthotels.RoomGuestsMapping;
import com.tajawal.apis.dto.HotelJsonData;
import com.tajawal.apis.dto.hotelPayload.GeneratePayload;
import com.tajawal.apis.expectations.GatewayErrorExpectations;
import com.tajawal.apis.providers.GeoSuggestHotelProvider;
import com.tajawal.apis.providers.RequestHotelProvider;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchHotelSteps extends BaseSteps<SearchHotelSteps> {
    private Response geoSuggestHotelResponse;
    private Response hotelSearchResponse;
    private String endpoint;
    private String uriPath;
    private String payload;
    private String checkInDateFromToday;
    private String checkOutDateFromToday;
    @Autowired
    private GeoSuggestHotelProvider geoSuggestHotelProvider;
    @Autowired
    private RequestHotelProvider requestHotelProvider;
    @Autowired
    private GeneratePayload generatePayload;
    @Autowired
    private HotelJsonData hotelJsonData;

    @Step
    public SearchHotelSteps GivenISearchHotel(QueryHotelSearch queryHotelSearch){
        queryParamUtil.addQueryParam(queryHotelSearch.getKey() , queryHotelSearch.getValue());
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getGetSearchGeoSuggestFullPath().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        addAllureLogs("Geo Suggestion Hotel Request" , uriPath);
        return this;
    }

    @Step
    public SearchHotelSteps AndIRetrieveHotelsByGeoSuggestion(){
        geoSuggestHotelResponse = searchHotelCalls.getHotelByGeoSuggestion(headerUtil.getHeaders(),uriPath);
        addAllureLogs("Geo Suggestion Hotel Response", geoSuggestHotelResponse.asString());
        return this;
    }

    @Step
    public SearchHotelSteps ThenISeeHotelListIsRetrieved(){
        assertThat.responseStatusIs(geoSuggestHotelResponse, 200);
        geoSuggestHotelProvider.setGeoSuggestHotelResponse(geoSuggestHotelResponse);
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyCityInAllHotels(QueryHotelSearch queryHotelSearch){
        geoSuggestHotelProvider.getHotels().
           forEach(hotel -> {
               assertThat.fieldIsEqual(queryHotelSearch.getValue().toLowerCase(), hotel.getCity().toLowerCase());
               addAllureLogs("City Name", hotel.getCity());
           });
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyCountryInAllHotels(QueryHotelSearch queryHotelSearch){
        geoSuggestHotelProvider.getLocations().
            forEach(location -> {
                assertThat.fieldIsEqual(queryHotelSearch.getValue().toLowerCase(),
                    location.getCountry().toLowerCase());
                addAllureLogs("Country Name", location.getCountry());
            });
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyDisplayTypeInHotelList(DisplayType displayType){
        geoSuggestHotelProvider.getHotels().
            forEach(hotel -> {
                assertThat.fieldIsEqual(displayType.name().toLowerCase(), hotel.getDisplayType().toLowerCase());
                addAllureLogs("Display Type", hotel.getDisplayType());
            });
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyDisplayTypeInLocationList(DisplayType displayType){
        geoSuggestHotelProvider.getLocations().
            forEach(hotel -> {
                assertThat.fieldIsEqual(displayType.name().toLowerCase(), hotel.getDisplayType().toLowerCase());
                addAllureLogs("Display Type", hotel.getDisplayType());
            });
        return this;
    }

    @Step
    public SearchHotelSteps AndIGetHotelNamesInTheList(){
        geoSuggestHotelProvider.getHotels().
            forEach(hotel -> {
                assertThat.fieldDataIsNotEmpty(hotel.getName());
                System.out.println(hotel.getName());
                addAllureLogs("Hotel Name", hotel.getName());
            });
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyEmptyHotelAndLocationList(){
        assertThat.fieldIsEqual(geoSuggestHotelProvider.getHotels().size() , 0);
        assertThat.fieldIsEqual(geoSuggestHotelProvider.getLocations().size() , 0);
        return this;
    }

    @Step
    public SearchHotelSteps GivenIGenerateSearchHotelPayload(RoomGuestsMapping roomGuestsMapping ,
        int checkInDaysFromToday , int checkOutDaysFromToday){
        checkInDateFromToday = testUtil.getFutureDateInFormat("dd-MM-yyyy",checkInDaysFromToday);
        checkOutDateFromToday = testUtil.getFutureDateInFormat("dd-MM-yyyy",checkOutDaysFromToday);

        payload = generatePayload.getRequestBody(roomGuestsMapping , checkInDaysFromToday ,checkOutDaysFromToday);
        return this;
    }

    @Step
    public SearchHotelSteps GivenIGenerateSearchHotelPayload(String checkInDate , String checkOutDate){
        payload = hotelJsonData.loadHotelJsonData(checkInDate,checkOutDate).toString();
        return this;
    }

    @Step
    public SearchHotelSteps AndISearchForHotel(){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getGetSearchRequestFullPath().get();
        hotelSearchResponse = searchHotelCalls.requestHotel(payload , endpoint , headerUtil.getHeaders());
        addAllureLogs("Search Hotel Request", endpoint);
        addAllureLogs("Search Hotel Payload", payload);
        return this;
    }

    @Step
    public SearchHotelSteps ThenISeeSearchRequestSuccessful(){
        assertThat.responseStatusIs(hotelSearchResponse, 200);
        requestHotelProvider.setRequestHotelResponse(hotelSearchResponse);
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyTypeInHotelSearch(DisplayType displayType){
        assertThat.fieldIsEqual(requestHotelProvider.getType(), displayType.name().toLowerCase());
        addAllureLogs("Display type ", requestHotelProvider.getType());
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyDestinationInQuery(RoomGuestsMapping roomGuestsMapping){
        String[] query = requestHotelProvider.getQuery().split("/");
        assertThat.fieldIsEqual(query[0], roomGuestsMapping.getDestination().toLowerCase());
        addAllureLogs("Destination", query[0]);
        return this;
    }

    @Step
    public SearchHotelSteps AndIVerifyDatesInQuery(){
        String[] query = requestHotelProvider.getQuery().split("/");
        assertThat.fieldIsEqual(query[1], checkInDateFromToday);
        assertThat.fieldIsEqual(query[2], checkOutDateFromToday);
        addAllureLogs("Check In Date", checkInDateFromToday);
        addAllureLogs("Check Out Date", checkOutDateFromToday);
        return this;
    }

    @Step
    public SearchHotelSteps ThenIVerifyGatewayBadRequest( GatewayErrorExpectations error){
        assertThat.responseStatusIs(hotelSearchResponse, error.getErrorCode());
        requestHotelProvider.setRequestHotelResponse(hotelSearchResponse);
        verifyErrors(error ,requestHotelProvider );
        return this;
    }
}
