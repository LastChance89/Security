package com.sec.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserRoleExtractor implements ResultSetExtractor<List<SimpleGrantedAuthority>>{

	@Override
	public List<SimpleGrantedAuthority> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SimpleGrantedAuthority> roleList = new ArrayList<SimpleGrantedAuthority>();
		while(rs.next()) {
			roleList.add(new SimpleGrantedAuthority(rs.getString("ROLE")));
		}
		return roleList;
	}

}
