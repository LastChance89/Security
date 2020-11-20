package com.sec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	private String userName;	
	@Column(name="password")
	private String password;
	@Column(name="hint")
	private String hint; 
	
	@Transient
	private List<SimpleGrantedAuthority> roles;
	
	public User() {
		
	}
	
	public User (String userName, String password, String hint) {
		this.userName = userName;
		this.password = password;
		this.hint = hint;
	}
	
	public User (String userName, String password, String hint,List<SimpleGrantedAuthority> roles) {
		this.userName = userName; 
		this.password = password; 
		this.hint = hint; 
		this.roles = roles; 
	}
	public User (String userName,List<SimpleGrantedAuthority> roles) {
		this.userName = userName; 
		this.roles = roles; 
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	 public List<SimpleGrantedAuthority> getRoles() { 
		 return roles; 
	} 
	
	 public void setRoles(List<SimpleGrantedAuthority> roles){ 
		 this.roles = roles; 
	 }
	

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
}
