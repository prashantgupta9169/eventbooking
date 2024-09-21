package com.eventbooking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eventbooking.data.EventBookingRequest;
import com.eventbooking.data.EventBookingResponse;
import com.eventbooking.domain.Event;
import com.eventbooking.domain.EventRepository;
import com.eventbooking.exception.BadRequestException;
import com.eventbooking.service.EventBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventBookingServiceImpl implements EventBookingService {

    private final EventRepository eventRepository;

    @Override
    public EventBookingResponse bookEvent(EventBookingRequest eventBookingRequest) {
        Optional<Event> eventDb = eventRepository.findByName(eventBookingRequest.getName());
        if (eventDb.isPresent()) {
            throw new BadRequestException("Event already exists");
        }
        Event newEvent = Event.builder().name(eventBookingRequest.getName()).location(eventBookingRequest.getLocation())
                .startDate(eventBookingRequest.getStartDate()).endDate(eventBookingRequest.getEndDate())
                .totalTickets(eventBookingRequest.getTotalTickets())
                .ticketsAvailable(eventBookingRequest.getTotalTickets())
                .price(eventBookingRequest.getPrice()).isActive(eventBookingRequest.getIsActive()).build();

        Event event = this.eventRepository.save(newEvent);
        return EventBookingResponse.builder().id(event.getId()).totalTickets(newEvent.getTotalTickets())
                .ticketsAvailable(newEvent.getTicketsAvailable()).build();

    }

    @Override
    public EventBookingResponse getEventDetails(Long eventId) {
        Optional<Event> eventDb = eventRepository.findById(eventId);
        if (!eventDb.isPresent()) {
            throw new BadRequestException("Event not found");
        }
        Event event = eventDb.get();
        return convertEventDaoToResponse(event);
    }

    private EventBookingResponse convertEventDaoToResponse(Event event) {
        return EventBookingResponse.builder().id(event.getId()).name(event.getName()).location(event.getLocation())
                .startDate(event.getStartDate()).endDate(event.getEndDate()).totalTickets(event.getTotalTickets())
                .ticketsAvailable(event.getTicketsAvailable()).price(event.getPrice()).isActive(event.getIsActive())
                .build();
    }

    @Override
    public String deleteEvent(Long eventId) {
        Optional<Event> eventDb = eventRepository.findById(eventId);
        if (!eventDb.isPresent()) {
            throw new BadRequestException("Event not found");
        }
        eventRepository.deleteById(eventId);
        return "Event deleted successfully";
    }

    @Override
    public EventBookingResponse updateEventBookingDetails(Long eventId, EventBookingRequest eventBookingRequest) {
        Optional<Event> eventDb = eventRepository.findById(eventId);
        if (!eventDb.isPresent()) {
            throw new BadRequestException("Event not found");
        }
        Event event = eventDb.get();
        if (eventBookingRequest.getName() != null) {
            event.setName(eventBookingRequest.getName());
        }
        if (eventBookingRequest.getLocation() != null) {
            event.setLocation(eventBookingRequest.getLocation());
        }
        if (eventBookingRequest.getStartDate() != null) {
            event.setStartDate(eventBookingRequest.getStartDate());
        }
        if (eventBookingRequest.getEndDate() != null) {
            event.setEndDate(eventBookingRequest.getEndDate());
        }
        if (eventBookingRequest.getTotalTickets() != null) {
            event.setTotalTickets(eventBookingRequest.getTotalTickets());
            event.setTicketsAvailable(eventBookingRequest.getTotalTickets());
        }
        if (eventBookingRequest.getPrice() != null) {
            event.setPrice(eventBookingRequest.getPrice());
        }
        if (eventBookingRequest.getIsActive() != null) {
            event.setIsActive(eventBookingRequest.getIsActive());
        }
        Event updatedEvent = eventRepository.save(event);

        return convertEventDaoToResponse(updatedEvent);
    }

    @Override
    public List<EventBookingResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::convertEventDaoToResponse).toList();
    }

}
