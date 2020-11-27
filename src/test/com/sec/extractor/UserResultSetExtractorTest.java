package com.sec.extractor;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

public class UserResultSetExtractorTest {

	@Test
	public void extractDataTestResultFound() throws SQLException {
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
		UserResultSetExtractor userE = new UserResultSetExtractor(); 
		assertTrue(userE.extractData(rs));
	}

	@Test
	public void extractDataTestResultNotFound() throws SQLException {
		ResultSet rs = Mockito.mock(ResultSet.class);
		Mockito.when(rs.next()).thenReturn(false);
		UserResultSetExtractor userE = new UserResultSetExtractor(); 
		assertFalse(userE.extractData(rs));
	}

}
