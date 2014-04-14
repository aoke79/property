package com.sms.data.hr.people.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sms.data.hr.people.bean.HR;
import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.data.jdbc.JdbcTemplateForm;

public class HrDao {

	private JdbcTemplateEx jdbcTemplate;

	public HrDao(JdbcTemplateEx jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public HrDao() {
		this.jdbcTemplate = JdbcTemplateForm.getJdbcTemplateHR();
	}
	
	public List getHR() {
		String sql = "select * from fss where m_codename not in ('飞行员配偶','抚恤人员') and A01101 is not null and A01102 is not null";
		return jdbcTemplate.queryForList(sql);
	}





class HRRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		HR hr = new HR();
		hr.setA0101(rs.getString("A0101"));
		hr.setA01101(rs.getString("A01101"));
		hr.setA01148(rs.getString("A01148"));
		hr.setA0107(rs.getString("A0107"));
		hr.setA0111(rs.getString("A0111"));
		hr.setB_CODENAME(rs.getString("B_CODENAME"));
		hr.setC_CODENAME(rs.getString("C_CODENAME"));
		hr.setA0177(rs.getString("A0177"));
		hr.setA0141(rs.getString("A0141"));
		hr.setA0144(rs.getString("A0144"));
		hr.setA01149(rs.getString("A01149"));
		hr.setD_CODENAME(rs.getString("D_CODENAME"));
		hr.setI_DWDM(rs.getString("I_DWDM"));
		hr.setA01102(rs.getString("A01102"));
		hr.setB0100(rs.getString("B0100"));
		hr.setA01141(rs.getString("A01141"));
		hr.setG_CODENAME(rs.getString("G_CODENAME"));
		hr.setJ_CODENAME(rs.getString("J_CODENAME"));
		hr.setFIRSTNAME(rs.getString("FIRSTNAME"));
		hr.setMIDNAME(rs.getString("MIDNAME"));
		hr.setLASTNAME(rs.getString("LASTNAME"));
		return hr;
	}

}



}
