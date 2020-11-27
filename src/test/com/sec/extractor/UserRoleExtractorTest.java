package com.sec.extractor;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserRoleExtractorTest {

	@Test
	public void extractDataTest() throws SQLException {
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.when(rs.getString("ROLE")).thenReturn("testRole");
		UserRoleExtractor userRoleE = new UserRoleExtractor(); 
		SimpleGrantedAuthority testRole = new SimpleGrantedAuthority("testRole");
		List<SimpleGrantedAuthority> role = new ArrayList<SimpleGrantedAuthority>(); 
		role = userRoleE.extractData(rs);
		assertEquals(role.get(0),testRole);
		
	}

}
