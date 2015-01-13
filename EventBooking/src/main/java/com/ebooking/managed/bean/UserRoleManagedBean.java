package com.ebooking.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import com.ebooking.model.UserRole;
import com.ebooking.service.IUserService;

@ManagedBean(name = "userRoleMB")
@RequestScoped
public class UserRoleManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	// Spring User Service is injected...
	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	List<UserRole> userRoleList;

	private int id;
	private String role;

	public String addUserRole() {
		try {
			UserRole userRole = new UserRole();
			userRole.setId(getId());
			userRole.setRole(getRole());
			return SUCCESS;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return ERROR;
	}

	public void reset() {
		this.setId(0);
		this.setRole("");
	}

	/**
	 * Get UserRole List
	 * 
	 * @return List - UserRole List
	 */
	public List<UserRole> getUserRoleList() {
		userRoleList = new ArrayList<UserRole>();
		userRoleList.addAll(getUserService().getUserRoles());
		return userRoleList;
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
	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
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
	 * Get Role Name
	 * 
	 * @return String - Role Name
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Set Role Name
	 * 
	 * @param String
	 *            - Role Name
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
