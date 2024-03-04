package com.harshit1930815.ticketSystem.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.harshit1930815.ticketSystem.exception.InvalidOperationException;
import com.harshit1930815.ticketSystem.model.Ticket;
import com.harshit1930815.ticketSystem.repository.TicketRepository;
import com.harshit1930815.ticketSystem.service.TicketService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Ticket getTicketById(Long id) {
		return ticketRepository.findById(id)
				.orElseThrow(() -> new InvalidOperationException("Ticket not found with id: " + id));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Ticket createTicket(Ticket ticket) {
		validateTicket(ticket);
		ticket.setCreatedDate(LocalDateTime.now());
		ticket.setExpiry(LocalDateTime.now().plusDays(1));
		// ticket.setCreatedBy(ticket.getCreatedBy());
		// Save the ticket
		Ticket createdTicket = ticketRepository.save(ticket);
		// Log the successful creation
		logger.info("Ticket created successfully: {}", createdTicket);
		return createdTicket;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Ticket updateTicket(Long id, Ticket updatedTicket) {

		Ticket existingTicket = ticketRepository.findById(id)
				.orElseThrow(() -> new InvalidOperationException("Ticket not found with id: " + id));
		existingTicket.setTitle(updatedTicket.getTitle());
		existingTicket.setDescription(updatedTicket.getDescription());
		existingTicket.setCreatedDate(updatedTicket.getCreatedDate());
		existingTicket.setExpiry(updatedTicket.getCreatedDate().plusDays(1));
		validateTicket(existingTicket);
		Ticket UpdateTicket = ticketRepository.save(existingTicket);
		// Log the successful creation
		logger.info("Ticket created successfully: {}", UpdateTicket);
		return UpdateTicket;

	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void archiveTicketsOlderThanOneDay() {
		LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
		String sql = "DELETE FROM Ticket WHERE createdDate < ?";
		jdbcTemplate.update(sql, oneDayAgo);
	}

	public static void validateTicket(Ticket ticket) {
		try {
			// Check if the ticket is null
			Optional.ofNullable(ticket).orElseThrow(() -> {
				logger.error("Ticket cannot be null");
				return new InvalidOperationException("Ticket cannot be null");
			});

			// Check if the title is blank
			Optional.ofNullable(ticket.getTitle()).filter(title -> !title.trim().isEmpty()).orElseThrow(() -> {
				logger.error("Ticket title cannot be blank");
				return new InvalidOperationException("Ticket title cannot be blank");
			});

			// Check if the description is blank
			Optional.ofNullable(ticket.getDescription()).filter(description -> !description.trim().isEmpty())
					.orElseThrow(() -> {
						logger.error("Ticket description cannot be blank");
						return new InvalidOperationException("Ticket description cannot be blank");
					});

		} catch (InvalidOperationException e) {
			// Log and throw the exception if needed
			logger.error("Validation error: {}", e.getMessage());
			throw e;
		}
	}

}
