package com.ebooking.managed.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.dao.DataAccessException;

import com.ebooking.model.Event;
import com.ebooking.model.Ticket;
import com.ebooking.model.User;
import com.ebooking.service.IEventService;
import com.ebooking.service.ITicketService;
import com.ebooking.service.IUserService;

@ManagedBean(name = "ticketMB")
@RequestScoped
public class TicketManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{TicketService}")
	ITicketService ticketService;

	@ManagedProperty(value = "#{EventService}")
	IEventService eventService;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	List<Ticket> ticketList;

	private int id;
	private String title;
	private int eventId;
	private int userId;
	private String qrdata;

	public String addTicket() {
		try {
			Ticket ticket = new Ticket();
			ticket.setId(getId());
			ticket.setTitle(getTitle());

			Event ev = eventService.getEventById(getEventId());
			ticket.setEvent(ev);

			User ur = userService.getUserById(getUserId());
			ticket.setUser(ur);

			setQrdata("T" + getId() + "E" + getEventId() + "U" + getUserId());

			ticket.setQrdata(getQrdata());
			getTicketService().addTicket(ticket);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}

	public StreamedContent getCode() {
		String data = getQrdata();
		if (getQrdata() == null || getQrdata() == "")
			data = "TXXEXXUXX";
		ByteArrayOutputStream out = QRCode.from(data).to(ImageType.PNG)
				.stream();
		return new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()));
	}

	public void reset() {
		this.setId(0);
		this.setTitle("");
		this.setEventId(0);
		this.setUserId(0);
		this.setQrdata("");
	}

	public ITicketService getTicketService() {
		return ticketService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public IEventService getEventService() {
		return eventService;
	}

	public void setTicketService(ITicketService ticketService) {
		this.ticketService = ticketService;
	}

	public void setEventService(IEventService eventService) {
		this.eventService = eventService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<Ticket> getUserList() {
		ticketList = new ArrayList<Ticket>();
		ticketList.addAll(getTicketService().getTickets());
		return ticketList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getQrdata() {
		return qrdata;
	}

	public void setQrdata(String qrdata) {
		this.qrdata = qrdata;
	}
}
