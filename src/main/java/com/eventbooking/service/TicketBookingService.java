package com.eventbooking.service;

import java.util.List;

import com.eventbooking.data.TicketBookingRequest;
import com.eventbooking.data.TicketBookingResponse;

public interface TicketBookingService {

    TicketBookingResponse bookTicket(TicketBookingRequest ticketBookingRequest);

    TicketBookingResponse getTicketDetails(Long bookingId);

    List<TicketBookingResponse> getBookingDetailsByEventId(Long eventId);

    List<TicketBookingResponse> getBookingDetailsByUserId(Long userId);

}
