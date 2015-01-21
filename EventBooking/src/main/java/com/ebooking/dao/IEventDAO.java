package com.ebooking.dao;

import java.sql.Date;
import java.util.List;

import com.ebooking.model.Category;
import com.ebooking.model.Event;

public interface IEventDAO {

	public void addEvent(Event event);

	public void updateEvent(Event event);

	public void deleteEvent(Event event);

	public Event getEvent(int id);

	public Event getEvent(Date date);

	public List<Event> getEvents();

	public void addCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Category category);
	
	public Category getCategory(int id);
	
	public List<Category> getCategories();
}
