package com.ebooking.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;
import com.ebooking.service.IUserService;

@ManagedBean(name="userMB")
@RequestScoped
public class UserManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	
	//Spring User Service is injected...
	@ManagedProperty(value="#{UserService}")
	IUserService userService;
	
	List<User> userList;
	
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	
	private UserRole userRole;
	
	/**
	 * Add User
	 * 
	 * @return String - Response Message
	 */
	public String addUser() {
		try {
			User user = new User();
			user.setId(getId());
			user.setName(getName());
			user.setSurname(getSurname());
			user.setEmail(getEmail());
			user.setLogin(getLogin());
			user.setPassword(getUserService().hashPassword(getPassword()));
			UserRole ur = getUserService().findRoleByName("ROLE_USER");
			user.setUserRole(ur);
			getUserService().addUser(user);
			return "/pages/success.jsf";
		} catch (DataAccessException e) {
			e.printStackTrace();
		} 	
		
		return ERROR;
	}
	
	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId(0);
		this.setName("");
		this.setSurname("");
		this.setEmail("");
		this.setLogin("");
		this.setPassword("");
		this.setUserRole(null);
	}
	
	/**
	 * Get User List
	 * 
	 * @return List - User List
	 */
	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
	
	/**
	 * Get User Service
	 * 
	 * @return IUserService - User Service
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * Set User Service
	 * 
	 * @param IUserService - User Service
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Set User List
	 * 
	 * @param List - User List
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set User Id
	 * 
	 * @param int - User Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get User Name
	 * 
	 * @return String - User Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set User Name
	 * 
	 * @param String - User Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get User Surname
	 * 
	 * @return String - User Surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Set User Surname
	 * 
	 * @param String - User Surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}