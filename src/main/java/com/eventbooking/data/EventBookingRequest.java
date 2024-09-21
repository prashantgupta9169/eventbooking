package com.eventbooking.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class EventBookingRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Location is mandatory")
    private String location;
    @NotNull(message = "Start date is mandatory")
    private LocalDateTime startDate;
    @NotNull(message = "End date is mandatory")
    private LocalDateTime endDate;
    @NotNull(message = "Total tickets is mandatory")
    private Integer totalTickets;
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
    private Boolean isActive;

}
