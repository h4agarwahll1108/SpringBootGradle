package com.harshit1930815.ticketSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.harshit1930815.ticketSystem.model.Ticket;
import com.harshit1930815.ticketSystem.service.TicketService;

import java.util.List;

@RequestMapping("/tickets")
@RestController
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping()
	public ResponseEntity<List<Ticket>> getAllTickets() {

		return ResponseEntity.ok(ticketService.getAllTickets());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
		Ticket ticket = ticketService.getTicketById(id);
		return (ticket != null) ? new ResponseEntity<>(ticket, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/add")
	public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
		Ticket createdTicket = ticketService.createTicket(ticket);
		return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);

	}

	// 5. Create a Put Rest Endpoint to update the Specific Ticket - 5 points
	@PutMapping("/{id}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket updatedTicket) {
		Ticket updated = ticketService.updateTicket(id, updatedTicket);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
}
