package com.ebooking.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebooking.dao.IUserDAO;
import com.ebooking.model.UserRole;

@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public UserDetails loadUserByUsername(final String login)
			throws UsernameNotFoundException {

		com.ebooking.model.User user = userDAO.getUser(login);
		List<GrantedAuthority> authorities = buildUserAuthority(user
				.getUserRole());

		return buildUserForAuthentication(user, authorities);
	}

	// Converts service.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.ebooking.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getLogin(), user.getPassword(), true, true, true,
				true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);
		return Result;
	}
}
