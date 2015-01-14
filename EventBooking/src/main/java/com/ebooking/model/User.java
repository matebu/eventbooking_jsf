package com.ebooking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserRole userRole;

	public User() {
	}

	public User(String name, String surname, String login, String password,
			String email) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	/**
	 * Get User Id
	 * 
	 * @return int - User Id
	 */
	@Column(name = "id", unique = true, nullable = false)
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
	@Column(name = "name", unique = false, nullable = true)
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
	@Column(name = "surname", unique = false, nullable = true)
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

	@Column(name = "login", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", unique = false, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JoinColumn(name = "userrole_id", nullable = false)
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Column(name = "email", unique = true, nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("id : ").append(getId());
		strBuff.append(", name : ").append(getName());
		strBuff.append(", surname : ").append(getSurname());
		return strBuff.toString();
	}
}
