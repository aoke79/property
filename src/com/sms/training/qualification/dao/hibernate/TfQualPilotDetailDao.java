package com.sms.training.qualification.dao.hibernate;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.sinoframe.common.util.SpringContextUtil;
import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.data.jdbc.ObjectRowMapper;

public class TfQualPilotDetailDao {

	DataSource datas = (DataSource) SpringContextUtil.getBean("jdbcdataSourcefss3");
	//实例化bean
	private JdbcTemplateEx jdbcTemplate = new JdbcTemplateEx(datas) ;
	public void insertPilotDetail(Map map){
		jdbcTemplate.insertMap(map,"TF_QUAL_PILOTDETAIL");
	}
}
