package com.eventbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.data.TicketBookingRequest;
import com.eventbooking.data.TicketBookingResponse;
import com.eventbooking.service.TicketBookingService;

@RestController
@RequestMapping("/bookings")
public class TicketBookingApiResource {

    @Autowired
    private TicketBookingService ticketBookingService;

    @PostMapping
    public ResponseEntity<TicketBookingResponse> bookTickets(
            @RequestBody @Validated TicketBookingRequest ticketBookingRequest) {
        TicketBookingResponse ticketBookingResponse = ticketBookingService.bookTicket(ticketBookingRequest);
        return ResponseEntity.ok(ticketBookingResponse);
    }

    @GetMapping
    @RequestMapping("/{bookingId}")
    public ResponseEntity<TicketBookingResponse> getBookingDetailsById(@PathVariable Long bookingId) {
        TicketBookingResponse ticketBookingResponse = ticketBookingService.getTicketDetails(bookingId);
        return ResponseEntity.ok(ticketBookingResponse);
    }

    @GetMapping
    @RequestMapping("/users/{userId}")
    public ResponseEntity<List<TicketBookingResponse>> getBookingDetailsByUserId(@PathVariable Long userId) {
        List<TicketBookingResponse> ticketBookingResponse = ticketBookingService.getBookingDetailsByUserId(userId);
        return ResponseEntity.ok(ticketBookingResponse);
    }

    @GetMapping
    @RequestMapping("/events/{eventId}")
    public ResponseEntity<List<TicketBookingResponse>> getBookingDetailsByEventId(@PathVariable Long eventId) {
        List<TicketBookingResponse> ticketBookingResponse = ticketBookingService.getBookingDetailsByEventId(eventId);
        return ResponseEntity.ok(ticketBookingResponse);
    }

}
