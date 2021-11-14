package com.demo;

import com.demo.dto.TicketDTO;
import com.demo.entities.*;
import com.demo.exception.ModelNotFoundException;
import com.demo.repositories.TicketRepository;
import com.demo.services.TicketService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class TicketServiceTest {

	@Autowired
	TicketService service;


	@Autowired
	TicketRepository repo;

//	Test get Ticket

	@Test
	public void testGetTicketByValidId() {
		int id = 1;
//		Ticket ticket = new Ticket("userName",
//				"abi@gmail.com", 3, LocalDate.of(2021, 11, 12));
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(new Ticket()));

		try{
			Ticket t = service.getTicketById(id);
			Assertions.assertNotNull(t);
			MatcherAssert.assertThat(t, Matchers.instanceOf(Ticket.class));
		}catch (ModelNotFoundException ex){
			System.out.println(ex.getLocalizedMessage());
		}
	}

	@Test
	public void testGetTicketByInValidId() {
		int id = -1;
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.getTicketById(id);});
	}

//	Test create Ticket
	@Test
	public void testCreateTicket() {
		Ticket ticket = new Ticket();

		Mockito.when(repo.save(ticket)).thenReturn(ticket);

		if(service.createTicket(new TicketDTO()).equals(ticket)){
			System.out.println("Created successfully!");
		}
	}


}
