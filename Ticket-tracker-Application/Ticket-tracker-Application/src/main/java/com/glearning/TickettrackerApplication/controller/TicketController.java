package com.glearning.TickettrackerApplication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.glearning.TickettrackerApplication.model.Ticket;
import com.glearning.TickettrackerApplication.service.TicketService;
//TicketController.java
@Controller
@RequestMapping("/")
public class TicketController {
private TicketService ticketService;
	
	public TicketController(TicketService theTicketService) {
		ticketService = theTicketService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listTickets(Model theModel) {
		
		// get tickets from database
		List<Ticket> theTickets = ticketService.findAll();
		
		// add to the spring model
		theModel.addAttribute("tickets", theTickets);
		
		return "list-tickets";
	}
	
	@GetMapping("/view")
	public String viewTickets(@RequestParam("id") int theId, Model theModel) {
		
		// get tickets from database
		Ticket theTickets = ticketService.findById(theId);
		
		// add to the spring model
		theModel.addAttribute("tickets", theTickets);
		
		return "viewById";
	}
	
	@RequestMapping(path = {"/search"})
	 public String search(Ticket ticket, Model model, String title) {
	 
		 List<Ticket> theTickets = ticketService.findTicketsByTitle(title);
	   model.addAttribute("tickets", theTickets);
	
	  return "searchByTitle";
	 }
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Ticket theTicket = new Ticket();
		
		theModel.addAttribute("tickets", theTicket);
		
		return "ticket-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId,
									Model theModel) {
		
		// get the ticket from the service
		Ticket theTicket = ticketService.findById(theId);
		
		// set ticket as a model attribute to pre-populate the form
		theModel.addAttribute("tickets", theTicket);
		
		// send over to our form
		return "edit-form";			
	}
	
	
	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("tickets") Ticket theTicket) {
		
		// save the ticket
		ticketService.save(theTicket);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") int theId) {
		
		// delete the ticket
		ticketService.deleteById(theId);
		
		// redirect to /list
		return "redirect:/list";
		
	}
}