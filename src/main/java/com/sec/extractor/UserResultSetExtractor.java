package com.sec.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserResultSetExtractor implements ResultSetExtractor<Boolean> {

	@Override
	public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
		/*We dont care about the value, only if something is returned. If nothing returned,
		then the user and password is wrong. 
		*/
		if(rs.next()) {
			return true;
		}
		return false;
		
		
	}

}
