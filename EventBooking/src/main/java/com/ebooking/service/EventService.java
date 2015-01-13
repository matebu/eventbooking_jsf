package com.ebooking.service;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ebooking.dao.IEventDAO;
import com.ebooking.model.Event;

@Transactional
public class EventService implements IEventService {

	IEventDAO eventDAO;

	@Transactional
	public void addEvent(Event event) {
		getEventDAO().addEvent(event);
	}

	@Transactional
	public void updateEvent(Event event) {
		getEventDAO().updateEvent(event);
	}

	@Transactional
	public void deleteEvent(Event event) {
		getEventDAO().deleteEvent(event);
	}

	public Event getEventById(int id) {
		return getEventDAO().getEvent(id);
	}

	public Event getEventrByDate(Date date) {
		return getEventDAO().getEvent(date);
	}

	public List<Event> getEvents() {
		return getEventDAO().getEvents();
	}

	public IEventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
}
