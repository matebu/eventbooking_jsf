package com.ebooking.dao;

import java.util.List;

import com.ebooking.model.Ticket;

public interface ITicketDAO {

	public void addTicket(Ticket ticket);

	public void updateTicket(Ticket ticket);

	public void deleteTicket(Ticket ticket);

	public Ticket getTicket(int id);

	public List<Ticket> getTicketsByEventId(int eventId);

	public List<Ticket> getTicketsByUserId(int userId);

	public List<Ticket> getTickets();
}
