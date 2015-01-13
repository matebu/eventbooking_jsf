package com.ebooking.service;

import java.util.List;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;

public interface IUserService {
	
	/**
	 * Add User
	 * 
	 * @param  User user
	 */
	public void addUser(User user);
	
	/**
	 * Update User
	 * 
	 * @param  User user
	 */
	public void updateUser(User user);

	/**
	 * Delete User
	 * 
	 * @param  User user
	 */
	public void deleteUser(User user);
	
	/**
	 * Get User
	 * 
	 * @param  int User Id
	 */
	public User getUserById(int id);

	public User getUserByLogin(String login);
	
	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	public List<User> getUsers();
	
	public void addRole(UserRole userRole);
	
	public UserRole findRoleByName(String role);
	
	public List<UserRole> getUserRoles();
	
	public String hashPassword(String password);
}
