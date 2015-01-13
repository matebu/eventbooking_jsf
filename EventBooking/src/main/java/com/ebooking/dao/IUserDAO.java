package com.ebooking.dao;

import java.util.List;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;

public interface IUserDAO {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User getUser(int id);

	public User getUser(String login);

	public void addRole(UserRole userRole);

	public UserRole findRoleByName(String role);
	
	public UserRole findRoleById(int id);

	public List<User> getUsers();

	public List<UserRole> getUserRoles();
}
