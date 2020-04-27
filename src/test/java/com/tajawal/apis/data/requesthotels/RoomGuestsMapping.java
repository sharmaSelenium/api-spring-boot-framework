package com.tajawal.apis.data.requesthotels;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomGuestsMapping {

    ONE_ROOM_2_ADT_1_CHD_PARIS("Paris","1-2-1"),
    ONE_ROOM_2_ADT_0_CHD_PARIS("Paris","1-2-0"),
    TWO_ROOMS_2_ADT_2_CHD_2_ADT_1_CHD_PARIS("Paris","1-2-2#2-3-1"),
    THREE_ROOMS_1_ADT_1_CHD_2_ADT_2_CHD_1_ADT("Paris","1-1-1#2-2-2#3-1-0"),
    EMPTY_DESTINATION("","1-2-1"),;

    private String destination;
    private String roomGuestChdConfig;
}