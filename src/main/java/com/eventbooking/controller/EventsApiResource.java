package com.eventbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.data.EventBookingRequest;
import com.eventbooking.data.EventBookingResponse;
import com.eventbooking.service.EventBookingService;

@RestController
@RequestMapping("/events")
public class EventsApiResource {

    @Autowired
    private EventBookingService eventBookingService;

    @PostMapping
    public ResponseEntity<EventBookingResponse> createEvent(
            @RequestBody @Validated EventBookingRequest eventBookingRequest) {
        EventBookingResponse eventBookingResponse = eventBookingService.bookEvent(eventBookingRequest);
        return ResponseEntity.ok(eventBookingResponse);
    }

    @GetMapping
    @RequestMapping("/{eventId}")
    public ResponseEntity<EventBookingResponse> getEventDetails(@PathVariable Long eventId) {
        EventBookingResponse eventBookingResponse = eventBookingService.getEventDetails(eventId);
        return ResponseEntity.ok(eventBookingResponse);
    }

    @DeleteMapping
    @RequestMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        String response = eventBookingService.deleteEvent(eventId);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @RequestMapping("/update/{eventId}")
    public ResponseEntity<String> updateEventBookingDetails(@PathVariable Long eventId,
            @RequestBody EventBookingRequest eventBookingRequest) {
        eventBookingService.updateEventBookingDetails(eventId, eventBookingRequest);
        return ResponseEntity.ok("Updated");
    }

    @GetMapping
    public ResponseEntity<List<EventBookingResponse>> getAllEvents() {
        List<EventBookingResponse> responses = eventBookingService.getAllEvents();
        return ResponseEntity.ok(responses);
    }

}
