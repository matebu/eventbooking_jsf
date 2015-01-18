package com.ebooking.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.Hibernate;
import org.springframework.dao.DataAccessException;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;
import com.ebooking.service.IUserService;

@SuppressWarnings("restriction")
@ManagedBean(name = "userMB")
@ViewScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	List<User> userList;
	List<User> userEditList;

	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;

	private UserRole userRole;

	private boolean editable;

	/**
	 * Editing data
	 */

	public boolean isEditable() {
		return editable;
	}

	public void setEditableFalse(User user) {
		this.editable = false;
		getUserService().updateUser(user);
	}

	public void setEditable(boolean e) {
		this.editable = e;
	}

	public void changeEditable(User user) {
		editable = !editable;
	}

	/**
	 * Init
	 */

	@PostConstruct
	public void init() {
		userEditList = new ArrayList<User>(getUserService().getUsers());
	}

	public void updateChanged() {
		for (User user : userEditList) {
			User u = getUserService().getUserById(user.getId());
			Hibernate.initialize(u.getUserRole());
			if (u.getUserRole().getRole().compareTo("ROLE_ADMIN") != 0)
				u.assignData(user);
			getUserService().updateUser(u);
		}
	}

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
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public String addDefaultAdmin() {
		try {
			User user = new User();
			user.setId(0);
			user.setName("Mateusz");
			user.setSurname("Busiakiewicz");
			user.setEmail("admin@test_mail.com");
			user.setLogin("admin");
			user.setPassword(getUserService().hashPassword("admin"));
			UserRole ur = getUserService().findRoleByName("ROLE_ADMIN");
			user.setUserRole(ur);
			getUserService().addUser(user);
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public void updateUser() {
		User u = getUserService().getUserById(getId());
		u.setName(getName());
		u.setSurname(getSurname());
		u.setEmail(getEmail());
		u.setLogin(getLogin());
		getUserService().updateUser(u);
	}

	@SuppressWarnings("finally")
	public String deleteUser(User user) {
		try {
			getUserService().deleteUser(user);
		} catch (DataAccessException e) {
			e.printStackTrace();
		} finally {
			return "/pages/secure/users.jsf";
		}
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
	 * @param IUserService
	 *            - User Service
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * Set User List
	 * 
	 * @param List
	 *            - User List
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
	 * @param String
	 *            - User Name
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
	 * @param String
	 *            - User Surname
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

	public List<User> getUserEditList() {
		return userEditList;
	}

	public void setUserEditList(List<User> userEditList) {
		this.userEditList = userEditList;
	}

	private String searchLogin;

	public String getSearchLogin() {
		return searchLogin;
	}

	public void setSearchLogin(String searchLogin) {
		this.searchLogin = searchLogin;
	}
}