package com.tajawal.apis.expectations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GatewayErrorExpectations implements ErrorExpectations {

    BAD_REQUEST_EMPTY_DESTINATION(
        400,
        "[Gateway:``] Bad Request",
        "The destination field is required."),
    BAD_REQUEST_CHECK_IN_WRONG_FORMAT_DATE(
        400,
        "[Gateway:``] Bad Request",
        "The dates.checkin does not match the format d-m-Y."),
    BAD_REQUEST_CHECK_OUT_WRONG_FORMAT_DATE(
        400,
        "[Gateway:``] Bad Request",
        "The dates.checkout does not match the format d-m-Y."),
    BAD_REQUEST_CHECK_IN_INVALID_DATE(
        400,
        "[Gateway:``] Bad Request",
        "The dates.checkin is not a valid date."),
    BAD_REQUEST_CHECK_OUT_INVALID_DATE(
        400,
        "[Gateway:``] Bad Request",
        "The dates.checkout is not a valid date."),
    BAD_REQUEST_CHECK_IN_DATE_BEFORE_CHECK_OUT(
        400,
        "[Gateway:``] Bad Request",
        "The dates.checkout must be a date after dates.checkin.");

    private final int errorCode;
    private final String titleMessage;
    private final String detailMessage;


}