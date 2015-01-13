package com.ebooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETS")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String qrdata;

	@ManyToOne(fetch = FetchType.LAZY)
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title", unique = false, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "eventid")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Column(name = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "qrdata")
	public String getQrdata() {
		return qrdata;
	}

	public void setQrdata(String qrdata) {
		this.qrdata = qrdata;
	}
}
