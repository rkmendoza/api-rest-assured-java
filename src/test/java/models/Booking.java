package models;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }
}