package com.sec.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface SecurityService {
	public List<SimpleGrantedAuthority> getRoles(String userName);
}
