package com.ebooking.managed.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import com.ebooking.model.Event;
import com.ebooking.service.IEventService;

@ManagedBean(name = "eventMB")
@RequestScoped
public class EventManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{EventService}")
	IEventService eventService;

	List<Event> eventList;

	private int id;
	private String name;
	private String description;
	private String place;
	private Date date;
	private int ticketCount;
	private double price;

	public String addEvent() {
		try {
			Event event = new Event();
			event.setId(getId());
			event.setName(getName());
			event.setDate(getDate());
			event.setDescription(getDescription());
			event.setTicketCount(getTicketCount());
			event.setPrice(getPrice());
			getEventService().addEvent(event);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}

	public void reset() {
		this.setId(0);
		this.setName("");
		this.setDescription("");
		this.setPlace("");
		this.setTicketCount(0);
		this.setPrice(0.0);
		Date dateNow = new Date(new java.util.Date().getTime());
		this.setDate(dateNow);
	}

	public IEventService getEventService() {
		return this.eventService;
	}

	public List<Event> getUserList() {
		eventList = new ArrayList<Event>();
		eventList.addAll(getEventService().getEvents());
		return eventList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
