package com.sms.data.jdbc;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

import com.sinoframe.common.spring.ApplicationFactory;
import com.sms.data.jdbc.JdbcTemplateEx;

public class JdbcTemplateForm {

	private static Map map = new HashMap();

	public static DataSource getDateSource(String dateSourcebeanName) {
		DataSource dataSource = ApplicationFactory.getDataSource(dateSourcebeanName);
		return dataSource;

	}

	private static JdbcTemplateEx getJdbcTemplate(String beanName) {
		if (map.get(beanName) == null) {
			map.put(beanName, new JdbcTemplateEx(getDateSource(beanName)));
		}
		return (JdbcTemplateEx) map.get(beanName);

	}

	public static JdbcTemplateEx getJdbcTemplateHR() {
		return getJdbcTemplate("dataSourceHR");
	}
	
	public static JdbcTemplateEx getJdbcTemplateSMS() {
		return getJdbcTemplate("dataSource");
	}
	
	public static JdbcTemplateEx getJdbcTemplateOA() {
		return getJdbcTemplate("dataSourceOA");
	}
	
	public static JdbcTemplateEx getJdbcTemplateAHDB() {
		return getJdbcTemplate("dataSourceAHDB");
	}
	
	public static JdbcTemplateEx getJdbcTemplateRZ() {
		return getJdbcTemplate("dataSourceRZ");
	}
	
	public static JdbcTemplateEx getJdbcTemplateTJ() {
		return getJdbcTemplate("dataSourceTJ");
	}
	
	public static JdbcTemplateEx getJdbcTemplateFK() {
		return getJdbcTemplate("dataSourceFK");
	}

	
}
