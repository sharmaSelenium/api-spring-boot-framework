package com.tajawal.apis.dto.hotelPayload;


import com.google.gson.Gson;
import com.tajawal.apis.data.requesthotels.RoomGuestsMapping;
import com.tajawal.apis.utils.TestUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratePayload {

    @Autowired RequestPayload requestPayload;
    @Autowired TestUtil testUtil;

    public String getRequestBody(RoomGuestsMapping roomGuestsMapping , int checkInDaysFromToday ,
        int checkOutDaysFromToday) {
        String checkInDateFromToday = testUtil.getFutureDateInFormat("dd-MM-yyyy",checkInDaysFromToday);
        String checkOutDateFromToday = testUtil.getFutureDateInFormat("dd-MM-yyyy",checkOutDaysFromToday);

        return new Gson().toJson(requestPayload.
            destination(roomGuestsMapping.getDestination()).
            placeId("2433").
            dates(new Dates().
                checkout(checkOutDateFromToday).
                checkin(checkInDateFromToday)).
                room(getRooms(roomGuestsMapping)

        ));
    }

    private List<Guest> getGuests(int numberOfAdults , int numberOfChild){
        ArrayList<Guest> adtList = new ArrayList<>();
        ArrayList<Guest> chdList = new ArrayList<>();
        for( int i = 0 ; i < numberOfAdults ; i++){
            adtList.add(new Guest().type("ADT"));
        }
        for( int i = 0 ; i < numberOfChild ; i++){
            adtList.add(new Guest().type("CHD"));
        }
        adtList.addAll(chdList);
        return adtList;
    }

    private List<Room> getRooms(RoomGuestsMapping guestData){
        String[] roomConfig = guestData.getRoomGuestChdConfig().split("#");
        ArrayList<Room> roomList = new ArrayList<>();
        for(String room :roomConfig){
            String[] guest = room.split("-");
            if(guest.length == 3){
                roomList.add(new Room().guest(getGuests(Integer.valueOf(guest[1]), Integer.valueOf(guest[2]))));

            }
            else {roomList.add(new Room().guest(getGuests(Integer.valueOf(guest[1]) , 0)));
            }
        }

        return roomList;
    }
}
