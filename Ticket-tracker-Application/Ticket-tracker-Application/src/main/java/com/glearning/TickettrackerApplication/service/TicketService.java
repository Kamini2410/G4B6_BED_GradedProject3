package com.glearning.TickettrackerApplication.service;

import java.util.List;


import com.glearning.TickettrackerApplication.model.Ticket;

public interface TicketService {
public List<Ticket> findAll();
	
	public Ticket findById(long theId);
	
	public void save(Ticket theTicket);
	
	public void deleteById(long theId);
	
	public List<Ticket> findTicketsByTitle(String title);
}