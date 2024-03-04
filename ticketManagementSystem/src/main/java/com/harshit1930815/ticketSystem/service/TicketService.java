package com.harshit1930815.ticketSystem.service;

import java.util.List;

import com.harshit1930815.ticketSystem.model.Ticket;

public interface TicketService {

    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket createTicket(Ticket ticket);
    Ticket updateTicket(Long id, Ticket ticket);
    void archiveTicketsOlderThanOneDay();


}
