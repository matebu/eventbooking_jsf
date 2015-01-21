package com.ebooking.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ebooking.model.Category;
import com.ebooking.model.Event;

public class EventDAO implements IEventDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void addEvent(Event event) {
		getSessionFactory().getCurrentSession().save(event);
	}

	@Transactional
	public void updateEvent(Event event) {
		getSessionFactory().getCurrentSession().update(event);
	}

	@Transactional
	public void deleteEvent(Event event) {
		getSessionFactory().getCurrentSession().delete(event);
	}

	@SuppressWarnings("unchecked")
	public Event getEvent(int id) {
		List<Event> list = (List<Event>) getSessionFactory()
				.getCurrentSession().createQuery("from Event where id = ?")
				.setParameter(0, id).list();
		return (Event) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public Event getEvent(Date date) {
		List<Event> list = (List<Event>) getSessionFactory()
				.getCurrentSession().createQuery("from Event where date = ?")
				.setParameter(0, date).list();
		return (Event) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Event> getEvents() {
		List<Event> list = (List<Event>) getSessionFactory()
				.getCurrentSession().createQuery("from Event").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Category getCategory(int id) {
		List<Category> list = (List<Category>) getSessionFactory()
				.getCurrentSession().createQuery("from Category where id = ?")
				.setParameter(0, id).list();
		return (Category) list.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategories() {
		List<Category> list = (List<Category>) getSessionFactory()
				.getCurrentSession().createQuery("from Category").list();
		return list;
	}

	public void addCategory(Category category) {
		getSessionFactory().getCurrentSession().save(category);
	}

	public void updateCategory(Category category) {
		getSessionFactory().getCurrentSession().update(category);
	}

	public void deleteCategory(Category category) {
		getSessionFactory().getCurrentSession().delete(category);
	}

}
