package com.eventbooking.data;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketBookingResponse {

    private Long bookingId;
    private String status;
    private BigDecimal totalAmount;
    private Long eventId;
    private Long userId;
    private LocalDateTime bookingDate;

}
