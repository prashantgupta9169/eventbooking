package com.eventbooking.service;

import java.util.List;

import com.eventbooking.data.EventBookingRequest;
import com.eventbooking.data.EventBookingResponse;

public interface EventBookingService {

    EventBookingResponse bookEvent(EventBookingRequest eventBookingRequest);

    EventBookingResponse getEventDetails(Long eventId);

    String deleteEvent(Long eventId);

    EventBookingResponse updateEventBookingDetails(Long eventId, EventBookingRequest eventBookingRequest);

    List<EventBookingResponse> getAllEvents();

}
