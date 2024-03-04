package com.harshit1930815.ticketSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit1930815.ticketSystem.model.Ticket;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findById(Long id);
    Ticket findByTitle(String title);
}
