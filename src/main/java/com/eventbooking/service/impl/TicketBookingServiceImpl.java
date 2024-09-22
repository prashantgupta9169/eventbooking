package com.eventbooking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventbooking.data.TicketBookingRequest;
import com.eventbooking.data.TicketBookingResponse;
import com.eventbooking.domain.Event;
import com.eventbooking.domain.EventRepository;
import com.eventbooking.domain.Ticket;
import com.eventbooking.domain.TicketRepository;
import com.eventbooking.domain.User;
import com.eventbooking.domain.UserRepository;
import com.eventbooking.exception.NotFoundException;
import com.eventbooking.service.TicketBookingService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketBookingServiceImpl implements TicketBookingService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public TicketBookingResponse bookTicket(TicketBookingRequest ticketBookingRequest) {
        Long eventId = ticketBookingRequest.getEventId();
        Optional<Event> event = this.eventRepository.findByIdForUpdate(eventId);
        if (!event.isPresent()) {
            throw new NotFoundException("Event not found");
        }
        Event eventDetails = event.get();

        Optional<User> user = this.userRepository.findById(ticketBookingRequest.getUserId());
        if (!user.isPresent()) {
            throw new NotFoundException("User not found");
        }
        if (eventDetails.getTicketsAvailable() <= 0
                || eventDetails.getTicketsAvailable() < ticketBookingRequest.getNumberOfTicket()
                || eventDetails.getEndDate().isBefore(ticketBookingRequest.getBookingDate())) {
            throw new NotFoundException("Tickets not available");
        }

        Ticket ticket = Ticket.builder().eventId(ticketBookingRequest.getEventId())
                .userId(ticketBookingRequest.getUserId()).quantity(ticketBookingRequest.getNumberOfTicket())
                .purchaseDate(LocalDateTime.now()).status("BOOKED")
                .totalPrice(BigDecimal.valueOf(ticketBookingRequest.getNumberOfTicket())
                        .multiply(eventDetails.getPrice()))
                .build();
        eventDetails.setTicketsAvailable(eventDetails.getTicketsAvailable() - ticketBookingRequest.getNumberOfTicket());
        eventRepository.save(eventDetails);

        Ticket ticketDetails = ticketRepository.save(ticket);

        return getTicketBookingResponse(ticketDetails);

    }

    private TicketBookingResponse getTicketBookingResponse(Ticket ticket) {
        return TicketBookingResponse.builder().bookingId(ticket.getId()).eventId(ticket.getEventId())
                .userId(ticket.getUserId()).status(ticket.getStatus()).totalAmount(ticket.getTotalPrice())
                .bookingDate(ticket.getPurchaseDate()).build();
    }

    @Override
    public TicketBookingResponse getTicketDetails(Long bookingId) {
        Optional<Ticket> ticket = ticketRepository.findById(bookingId);
        if (!ticket.isPresent()) {
            throw new NotFoundException("Ticket not found");
        }
        return getTicketBookingResponse(ticket.get());
    }

    @Override
    public List<TicketBookingResponse> getBookingDetailsByEventId(Long eventId) {
        List<Ticket> tickets = ticketRepository.findByEventId(eventId);
        if (tickets.isEmpty()) {
            throw new NotFoundException("Tickets not found");
        }
        return tickets.stream().map(this::getTicketBookingResponse).toList();
    }

    @Override
    public List<TicketBookingResponse> getBookingDetailsByUserId(Long userId) {
        List<Ticket> tickets = ticketRepository.findByUserId(userId);
        if (tickets.isEmpty()) {
            throw new NotFoundException("Tickets not found");
        }
        return tickets.stream().map(this::getTicketBookingResponse).toList();
    }

}