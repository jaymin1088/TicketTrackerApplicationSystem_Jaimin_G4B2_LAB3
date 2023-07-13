package com.gl.ticketmanagementsystem.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.gl.ticketmanagementsystem.domain.Ticket;
import com.gl.ticketmanagementsystem.service.TicketService;

@Controller // This means that this class is a Controller
public class TicketController {

	@Autowired
	// This means to get the bean which is auto-generated by Spring
	// We will use an ticketService for each
	private TicketService ticketService;

	// display list of Tickets
	@GetMapping("/")
	// This means that this method will be executed when user sends GET Requests to
	// "/"
	// In our case, "http://localhost:8080/"
	public String viewHomePage(Model model) {

		// We can use this attribute "listTickets" to perform server-side rendering of
		// the HTML with using Thymeleaf.
		// We set all Tickets data as "listTickets"

		// model.addAttribute("listEmployees", employeeService.getAllEmployees());

		List<Ticket> tickets = ticketService.getAllTickets();

		for (Ticket ticket : tickets) {
			ticket.setDate(new Date()); // Set the current date for each ticket
		}

		model.addAttribute("listTickets", tickets);

		// shows the index.html template:
		return "index";
	}

//	Search the ticket by title and short description
	@GetMapping("/search")
	public String searchTickets(@RequestParam("keyword") String keyword, Model model) {
		List<Ticket> tickets = ticketService.getAllTickets();
		List<Ticket> searchResults = ticketService.searchTickets(keyword);
		for (Ticket ticket : tickets) {
			ticket.setDate(new Date()); // Set the current date for each Ticket
		}
		model.addAttribute("listTickets", searchResults);
		return "index";
	}

	// Home page display click on tickets link
	@GetMapping("/tickets")
	public RedirectView redirectToTicketsPage() {
		return new RedirectView("http://localhost:8080/");
	}

	// showNewTicketForm
	@GetMapping("/showNewTicketForm")
	// This means that this method will be executed when user sends GET Requests to
	// "/showNewTicketForm"
	// In our case, "http://localhost:8080/showNewTicketForm"
	public String showNewTicketForm(Model model) {
		Ticket ticket = new Ticket();

		// We can use this attribute "ticket" to perform server-side rendering of the
		// HTML with using Thymeleaf.
		// We set ticket object as "ticket"
		model.addAttribute("ticket", ticket);

		// shows the new_ticket.html template:
		return "new_ticket";
	}

	// add an new ticket
	@PostMapping("/saveTicket")
	// This means that this method will be executed when user sends POST Requests to
	// "/save ticket"
	// In our case, "http://localhost:8080/saveticket"
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		// @ModelAttribute binds the object called "ticket" of request body from the
		// POST request into the ticket parameter of the saveticket() method.

		ticketService.saveTicket(ticket);

		// after save the ticket data to database, redirect to "/"
		return "redirect:/";
	}

	// show update form
	@GetMapping("/showFormForUpdate/{id}")
	// This means that this method will be executed when user sends GET Requests to
	// "/showFormForUpdate/{ticket s id}"
	// In our case, "http://localhost:8080/showFormForUpdate/{ticket's id}"
	public String showUpdateForm(@PathVariable Long id, Model model) {
		// @PathVariable binds the {id} which the path of GET request contains into the
		// id parameter of showUpdateForm() method.

		Ticket ticket = ticketService.getTicketById(id);

		// We can use this attribute "ticket" to perform server-side rendering of the
		// HTML with using Thymeleaf.
		// We set ticket data as "ticket"
		model.addAttribute("ticket", ticket);

		// shows the update_ticket.html template:
		return "update_ticket";
	}

	// View the ticket by id
	@GetMapping("/viewTicket/{id}")
	public String viewTicket(@PathVariable Long id, Model model) {
		Ticket ticket = ticketService.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "view_ticket";
	}

	// delete the ticket by id
	@GetMapping("/deleteTicket/{id}")
	// This means that this method will be executed when user sends GET Requests to
	// "/delete/{ticket's id}"
	// In our case, "http://localhost:8080/delete/{ticket id}"
	public String deleteTicketById(@PathVariable Long id, Model model) {
		ticketService.deleteTicketById(id);

		// after delete the Ticket data from database, redirect to "/"
		return "redirect:/";
	}

}
