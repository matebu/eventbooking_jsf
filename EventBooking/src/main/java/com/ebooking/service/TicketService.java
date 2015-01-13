package com.ebooking.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ebooking.dao.ITicketDAO;
import com.ebooking.model.Ticket;

@Transactional
public class TicketService implements ITicketService {

	ITicketDAO ticketDAO;

	@Transactional
	public void addTicket(Ticket ticket) {
		getTicketDAO().addTicket(ticket);
	}

	@Transactional
	public void updateTicket(Ticket ticket) {
		getTicketDAO().updateTicket(ticket);
	}

	@Transactional
	public void deleteTicket(Ticket ticket) {
		getTicketDAO().deleteTicket(ticket);
	}

	public Ticket getTicket(int id) {
		return getTicketDAO().getTicket(id);
	}

	public List<Ticket> getTicketsByEventId(int eventId) {
		return getTicketDAO().getTicketsByEventId(eventId);
	}

	public List<Ticket> getTicketsByUserId(int userId) {
		return getTicketDAO().getTicketsByUserId(userId);
	}

	public List<Ticket> getTickets() {
		return getTicketDAO().getTickets();
	}

	public ITicketDAO getTicketDAO() {
		return ticketDAO;
	}

	public void setTicketDAO(ITicketDAO ticketDAO) {
		this.ticketDAO = ticketDAO;
	}
}
