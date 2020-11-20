package com.sec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sec.dao.UserDao;
import com.sec.service.SecurityService;

public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<SimpleGrantedAuthority> getRoles(String userName) {
		return userDao.getRoles(userName);
	}

	
}
