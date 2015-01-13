package com.ebooking.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ebooking.model.User;
import com.ebooking.model.UserRole;

public class UserDAO implements IUserDAO {

	private SessionFactory sessionFactory;

	/**
	 * Get Hibernate Session Factory
	 * 
	 * @return SessionFactory - Hibernate Session Factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Set Hibernate Session Factory
	 * 
	 * @param SessionFactory
	 *            - Hibernate Session Factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Add User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void addUser(User user) {
		getSessionFactory().getCurrentSession().save(user);
	}

	/**
	 * Delete User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void deleteUser(User user) {
		getSessionFactory().getCurrentSession().delete(user);
	}

	/**
	 * Update User
	 * 
	 * @param User
	 *            user
	 */
	@Transactional
	public void updateUser(User user) {
		getSessionFactory().getCurrentSession().update(user);
	}

	/**
	 * Get User
	 * 
	 * @param int User Id
	 * @return User
	 */
	@SuppressWarnings("unchecked")
	public User getUser(int id) {
		List<User> list = (List<User>) getSessionFactory().getCurrentSession()
				.createQuery("from User where id = ?").setParameter(0, id)
				.list();
		return (User) list.get(0);
	}

	/**
	 * Get User List
	 * 
	 * @return List - User list
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		List<User> list = (List<User>) getSessionFactory().getCurrentSession()
				.createQuery("from User").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public User getUser(String login) {
		List<User> list = (List<User>) getSessionFactory().getCurrentSession()
				.createQuery("from User where login = ?")
				.setParameter(0, login).list();
		return (User) list.get(0);
	}

	public void addRole(UserRole userRole) {
		sessionFactory.getCurrentSession().save(userRole);
	}

	@SuppressWarnings("unchecked")
	public UserRole findRoleByName(String role) {

		List<UserRole> userRole = new ArrayList<UserRole>();

		userRole = sessionFactory.getCurrentSession()
				.createQuery("from UserRole where role=?")
				.setParameter(0, role).list();

		if (userRole.size() > 0) {
			return userRole.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public UserRole findRoleById(int id) {

		List<UserRole> userRole = new ArrayList<UserRole>();

		userRole = sessionFactory.getCurrentSession()
				.createQuery("from UserRole where id=?")
				.setParameter(0, id).list();

		if (userRole.size() > 0) {
			return userRole.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoles() {
		List<UserRole> list = (List<UserRole>) getSessionFactory()
				.getCurrentSession().createQuery("from UserRole").list();
		return list;
	}
}
