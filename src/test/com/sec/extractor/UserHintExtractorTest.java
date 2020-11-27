package com.sec.extractor;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class UserHintExtractorTest {
	
	@Test
	public void extractDataTest() throws SQLException {
		ResultSet rs = Mockito.mock(ResultSet.class);
		//NOTE: Has to have the Return False or else infinite loop that crashed computer. 
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		Mockito.when( rs.getString("HINT")).thenReturn("TestHint");
		UserHintExtractor hintE = new UserHintExtractor(); 
		assertEquals("TestHint", hintE.extractData(rs));

	}

}
