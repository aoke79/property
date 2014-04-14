package com.sms.data.hr.people.dao;

import java.util.*;

import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.data.jdbc.ObjectRowMapper;

public class SmsDao {

	private JdbcTemplateEx jdbcTemplate;

	public SmsDao(JdbcTemplateEx jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List getDeptHR2SMS() {
		String sql = "select * from intf_hr_sms_dept ";
		return jdbcTemplate.queryForList(sql);
	}
	
	public List getPeopleHR() {
		String sql = " select t.hrid from cm_people t where t.hrid is not null ";
		return jdbcTemplate.query(sql, new ObjectRowMapper());
	}
	
	public Map getPeople(String hrid) {
		String sql = " select * from cm_people t where t.hrid='"+hrid+"' ";
		return (Map)jdbcTemplate.queryForList(sql).get(0);
	}
	
	public void setSTATUS() {
		
		jdbcTemplate.execute(" update cm_people t set t.status='2' where t.hrid is not null ");
		
	}

	public void updatePeople(Map map) {

		jdbcTemplate.updateMap(map, "CM_PEOPLE", "HRID");
		
	}

	public void insertPeople(Map map) {

		jdbcTemplate.insertMap(map, "CM_PEOPLE");
		
	}
}
