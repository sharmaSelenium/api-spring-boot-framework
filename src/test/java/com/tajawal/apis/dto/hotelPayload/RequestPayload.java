package com.tajawal.apis.dto.hotelPayload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class RequestPayload {

    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("room")
    @Expose
    private List<Room> room = null;
    @SerializedName("placeId")
    @Expose
    private String placeId;
}
