package com.sms.data.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ObjectRowMapper implements RowMapper {
	private String column;

	public ObjectRowMapper() {
		column = null;
	}

	public ObjectRowMapper(String column) {
		this.column = column;
	}

	public Object mapRow(ResultSet rs, int i) throws SQLException {
		if (column == null)
			return rs.getObject(1);
		else
			return rs.getObject(column);
	}

}
