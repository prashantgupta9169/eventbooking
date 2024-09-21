package com.eventbooking.data;

import java.time.LocalDateTime;

import com.eventbooking.enums.PaymentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingRequest {

    @NotNull(message = "User Id is mandatory")
    private Long userId;
    @NotNull(message = "Event Id is mandatory")
    private Long eventId;
    @NotNull(message = "Booking date is mandatory")
    private LocalDateTime bookingDate;
    @NotNull(message = "Number of Ticket is mandatory")
    private Integer numberOfTicket;
    private PaymentType paymentType;

}
