package com.sec.util;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sec.model.User;

public class AuthenticationTokenUtilTest {
	
	
	private AuthenticationTokenUtil authenticationTokenUtil;
	private List<SimpleGrantedAuthority> roles= new ArrayList<SimpleGrantedAuthority>();
	
	
	@Before
	public void setUp() throws Exception {
		roles.add(new  SimpleGrantedAuthority("USER"));
		authenticationTokenUtil = new AuthenticationTokenUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createTokenAndVerifyTest() {
		User user = new User("TestUser","Password","hint",roles);
		String token = authenticationTokenUtil.createToken(user);
		String userName = authenticationTokenUtil.getUserNameFromToken(token);
		assertEquals(userName,"TestUser");
		//Only need to confirm the Experation date has been created. 
		assertNotNull(authenticationTokenUtil.getExperationDateFromToken(token));
		assertFalse(authenticationTokenUtil.isTokenExpired(token));
		authenticationTokenUtil.validate(token, user); 
	}

}
