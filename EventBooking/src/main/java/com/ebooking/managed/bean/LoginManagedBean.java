package com.ebooking.managed.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name="loginMB")
@RequestScoped
public class LoginManagedBean implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private String login; 
    private String password;

    @ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    public String loginUser() {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken(this.getLogin(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "/pages/unsecure/index.jsf";
        }
        return "/pages/unsecure/index.jsf";
    }

    public String cancel() {
        return null;
    }

    public String logout(){
        SecurityContextHolder.clearContext();
        return "/pages/unsecure/index.jsf";
    }
 
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
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
}