package com.sec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sec.service.SecurityService;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private SecurityService securityService;
	 
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> userRoles = securityService.getRoles(userName);
		return new User(userName, "", userRoles);
	}	
}
