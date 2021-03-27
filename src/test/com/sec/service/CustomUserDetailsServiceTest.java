package com.sec.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.sec.service.impl.CustomUserDetailsServiceImpl;

@RunWith(PowerMockRunner.class)
public class CustomUserDetailsServiceTest {

	@Mock
	private SecurityService service;

	@InjectMocks 
	private CustomUserDetailsServiceImpl customUdService;
	
	private final String testUserName = "testUser";
	
	@Test
	public void loadUserByUsernameTest() {

		List<SimpleGrantedAuthority> userRoles = new ArrayList<SimpleGrantedAuthority>() {
			{
				add(new SimpleGrantedAuthority("testRole"));
				add(new SimpleGrantedAuthority("testRole2"));
			}
		};
		User testUser = new User(testUserName, "", userRoles);
		Mockito.when(service.getRoles(testUserName)).thenReturn(userRoles);
		assertEquals(customUdService.loadUserByUsername(testUserName), testUser);
	}

}
