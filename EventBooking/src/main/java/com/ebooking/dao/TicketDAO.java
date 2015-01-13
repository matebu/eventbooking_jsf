package com.ebooking.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ebooking.model.Ticket;

public class TicketDAO implements ITicketDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void addTicket(Ticket ticket) {
		getSessionFactory().getCurrentSession().save(ticket);
	}

	@Transactional
	public void updateTicket(Ticket ticket) {
		getSessionFactory().getCurrentSession().update(ticket);
	}

	@Transactional
	public void deleteTicket(Ticket ticket) {
		getSessionFactory().getCurrentSession().delete(ticket);
	}

	@SuppressWarnings("unchecked")
	public Ticket getTicket(int id) {
		List<Ticket> list = (List<Ticket>) getSessionFactory()
				.getCurrentSession().createQuery("from Ticket where id = ?")
				.setParameter(0, id).list();
		return (Ticket) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsByEventId(int eventId) {
		List<Ticket> list = (List<Ticket>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Ticket where eventid = ?")
				.setParameter(0, eventId).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketsByUserId(int userId) {
		List<Ticket> list = (List<Ticket>) getSessionFactory()
				.getCurrentSession()
				.createQuery("from Ticket where userid = ?")
				.setParameter(0, userId).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> getTickets() {
		List<Ticket> list = (List<Ticket>) getSessionFactory()
				.getCurrentSession().createQuery("from Ticket").list();
		return list;
	}
}
