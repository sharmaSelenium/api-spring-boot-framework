package com.tajawal.apis.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tajawal.apis.apicalls.SearchHotelCalls;
import com.tajawal.apis.commons.Endpoints;
import com.tajawal.apis.expectations.ErrorExpectations;
import com.tajawal.apis.providers.RequestHotelProvider;
import com.tajawal.apis.utils.Assert;
import com.tajawal.apis.utils.HeaderUtil;
import com.tajawal.apis.utils.QueryParamUtil;
import com.tajawal.apis.utils.TestUtil;
import com.tajawal.apis.utils.URIBuilder;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSteps<T extends BaseSteps<T>> {
    public Response response;

    @Autowired
    Assert assertThat;
    @Autowired
    Endpoints endPoints;
    @Autowired
    HeaderUtil headerUtil;
    @Autowired
    QueryParamUtil queryParamUtil;
    @Autowired
    URIBuilder uriBuilder;
    @Autowired
    TestUtil testUtil;
    @Autowired
    SearchHotelCalls searchHotelCalls;

    public void verifyErrors(ErrorExpectations errorExpectations, RequestHotelProvider errorProvider) {

        assertEquals(errorExpectations.getErrorCode(), errorProvider.getStatus(),
            "\nExpected Status Code: " + errorExpectations.getErrorCode()
                + " \ndoes not match with\nActual Status Code: " + errorProvider.getStatus());

        addAllureLogs("Error Code is ", String.valueOf(errorProvider.getStatus()));

        assertEquals(errorExpectations.getTitleMessage(), errorProvider.getTitle(),
            "\nExpected Status Code: " + errorExpectations.getErrorCode()
                + " \ndoes not match with\nActual Title message: " + errorProvider.getTitle());
        addAllureLogs("Error Title Message",errorProvider.getTitle());

        if(errorProvider.getDestinationDetails()!= null){
            assertEquals(errorProvider.getDestinationDetails().get(0), errorExpectations.getDetailMessage(),
                "\nExpected Message: " + errorExpectations.getDetailMessage()
                    + " \ndoes not contains\nActual Message: " + errorProvider.getDestinationDetails().get(0));
        }

        if(errorProvider.getDestinationDetails()!= null){
            assertEquals(errorProvider.getDestinationDetails().get(0), errorExpectations.getDetailMessage(),
                "\nExpected Message: " + errorExpectations.getDetailMessage()
                    + " \ndoes not contains\nActual Message: " + errorProvider.getDestinationDetails().get(0));
        }

    }


    public void addAllureLogs(String attachmentName , String content){
        Allure.addAttachment(attachmentName , content);
    }
}