package com.eventbooking.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(Long bookingId);

    List<Ticket> findByEventId(Long eventId);

    List<Ticket> findByUserId(Long userId);
}
