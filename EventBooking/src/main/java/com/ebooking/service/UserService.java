package com.ebooking.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;
import com.ebooking.dao.IUserDAO;

@Transactional
@ApplicationScoped
public class UserService implements IUserService {

	// UserDAO is injected...
	IUserDAO userDAO;

	/**
	 * Add User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void addUser(User user) {
		getUserDAO().addUser(user);
	}

	/**
	 * Delete User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void deleteUser(User user) {
		getUserDAO().deleteUser(user);
	}

	/**
	 * Update User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void updateUser(User user) {
		getUserDAO().updateUser(user);
	}

	/**
	 * Get User
	 * 
	 * @param int User Id
	 */
	public User getUserById(int id) {
		return getUserDAO().getUser(id);
	}

	/**
	 * Get User List
	 * 
	 */
	public List<User> getUsers() {
		return getUserDAO().getUsers();
	}

	/**
	 * Get User DAO
	 * 
	 * @return IUserDAO - User DAO
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Set User DAO
	 * 
	 * @param IUserDAO
	 *            - User DAO
	 */
	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUserByLogin(String login) {
		return getUserDAO().getUser(login);
	}

	public void addRole(UserRole userRole) {
		getUserDAO().addRole(userRole);
	}

	public UserRole findRoleByName(String role) {
		return getUserDAO().findRoleByName(role);
	}

	public String hashPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public List<UserRole> getUserRoles() {
		return getUserDAO().getUserRoles();
	}
}
