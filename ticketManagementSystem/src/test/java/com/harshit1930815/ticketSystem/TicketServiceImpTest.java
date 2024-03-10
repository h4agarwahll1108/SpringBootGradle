package com.harshit1930815.ticketSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.harshit1930815.ticketSystem.exception.InvalidOperationException;
import com.harshit1930815.ticketSystem.model.Ticket;
import com.harshit1930815.ticketSystem.repository.TicketRepository;
import com.harshit1930815.ticketSystem.service.impl.TicketServiceImpl;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private TicketServiceImpl ticketService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllTickets() {
		Ticket ticket1 = new Ticket(1L, "Title 1", "Description 1", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));
		Ticket ticket2 = new Ticket(2L, "Title 2", "Description 2", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));

		when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

		List<Ticket> tickets = ticketService.getAllTickets();

		assertEquals(2, tickets.size());
		assertEquals(ticket1, tickets.get(0));
		assertEquals(ticket2, tickets.get(1));

		verify(ticketRepository, times(1)).findAll();
	}

	@Test
	void getTicketById() {
		Long ticketId = 1L;
		Ticket ticket = new Ticket(ticketId, "Test Title", "Test Description", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));

		when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

		Ticket result = ticketService.getTicketById(ticketId);

		assertEquals(ticket, result);

		verify(ticketRepository, times(1)).findById(ticketId);
	}

	@Test
	void createTicket() {
		Ticket inputTicket = new Ticket(1L, "Test Title", "Test Description", null, null);
		Ticket savedTicket = new Ticket(1L, "Test Title", "Test Description", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));

		when(ticketRepository.save(inputTicket)).thenReturn(savedTicket);

		Ticket result = ticketService.createTicket(inputTicket);

		assertEquals(savedTicket, result);

		verify(ticketRepository, times(1)).save(inputTicket);
		verify(jdbcTemplate, never()).update(anyString(), any(LocalDateTime.class));
	}

	@Test
	void updateTicket() {
		Long ticketId = 1L;
		Ticket existingTicket = new Ticket(ticketId, "Existing Title", "Existing Description", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));
		Ticket updatedTicket = new Ticket(ticketId, "Updated Title", "Updated Description", LocalDateTime.now(),
				LocalDateTime.now().plusDays(1));

		when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(existingTicket));
		when(ticketRepository.save(existingTicket)).thenReturn(updatedTicket);

		Ticket result = ticketService.updateTicket(ticketId, updatedTicket);

		assertEquals(updatedTicket, result);

		verify(ticketRepository, times(1)).findById(ticketId);
		verify(ticketRepository, times(1)).save(existingTicket);
	}

	@Test
	void archiveTicketsOlderThanOneDay() {
		ticketService.archiveTicketsOlderThanOneDay();

		verify(jdbcTemplate, times(1)).update(anyString(), any(LocalDateTime.class));
	}

	@Test
	void validateTicket_NullTicket_ThrowsException() {
		assertThrows(InvalidOperationException.class, () -> ticketService.validateTicket(null));
	}

	@Test
	void validateTicket_BlankTitle_ThrowsException() {
		Ticket ticket = new Ticket(1L, "", "Description", LocalDateTime.now(), LocalDateTime.now().plusDays(1));

		assertThrows(InvalidOperationException.class, () -> ticketService.validateTicket(ticket));
	}

	@Test
	void validateTicket_BlankDescription_ThrowsException() {
		Ticket ticket = new Ticket(1L, "Title", "", LocalDateTime.now(), LocalDateTime.now().plusDays(1));

		assertThrows(InvalidOperationException.class, () -> ticketService.validateTicket(ticket));
	}
}
