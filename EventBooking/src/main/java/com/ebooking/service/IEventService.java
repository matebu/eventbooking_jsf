package com.ebooking.service;

import java.sql.Date;
import java.util.List;

import com.ebooking.model.Event;

public interface IEventService {

	public void addEvent(Event event);

	public void updateEvent(Event user);

	public void deleteEvent(Event user);

	public Event getEventById(int id);

	public Event getEventrByDate(Date date);

	public List<Event> getEvents();
}
