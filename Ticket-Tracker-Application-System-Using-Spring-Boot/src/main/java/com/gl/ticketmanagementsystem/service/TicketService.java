package com.gl.ticketmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.ticketmanagementsystem.domain.Ticket;
import com.gl.ticketmanagementsystem.repository.TicketRepository;

@Service // This means that this class is a service
public class TicketService {
	@Autowired
	// This means to get the bean which is auto-generated by Spring
	// We will use it to handle the data
	TicketRepository ticketRepository;

	// get all Ticket
	public List<Ticket> getAllTickets() {

		// find all Ticket data, then return it
		return ticketRepository.findAll();
	}

	// save an Ticket
	public void saveTicket(Ticket tckt) {
		// save Ticket data to database
		ticketRepository.save(tckt);
	}

	// get Ticket by id
	public Ticket getTicketById(Long id) {
		// find an Ticket by id
		Ticket tckt = ticketRepository.getById(id);

		// if there is not an Ticket who has the id, throw the error.
		if (tckt == null) {
			throw new RuntimeException("Ticket not found");
		}
		return tckt;
	}

	// delete Ticket by id
	public String deleteTicketById(Long id) {
		// find an Ticket by id
		Ticket tckt = ticketRepository.getById(id);

		// if there is not an Ticket who has the id, throw the error.
		if (tckt == null) {
			throw new RuntimeException("Ticket not found");
		}

		// delete an Ticket who has the id from the database
		ticketRepository.deleteById(id);
		return "Deleted: " + tckt.getTitle() + " " + tckt.getContent();
	}

	// Search Ticket by title and short description
	public List<Ticket> searchTickets(String keyword) {
		return ticketRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
	}

}