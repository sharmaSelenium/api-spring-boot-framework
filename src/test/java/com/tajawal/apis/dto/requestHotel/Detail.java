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
public class Detail {
    @JsonProperty("dates.checkin")
    private List<String> datesCheckin = null;
    @JsonProperty("dates.checkout")
    private List<String> datesCheckout = null;
    @JsonProperty("destination")
    private List<String> destination = null;

}
