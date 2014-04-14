package com.sms.data.hr.people.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.dao.DataAccessException;

import com.sms.data.hr.people.PeopleConstant;
import com.sms.data.hr.people.dao.*;
import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.data.jdbc.JdbcTemplateForm;

public class PeopleBS {
	
	private HrDao hr;

	private SmsDao sms;
	
	List existPeople  = new ArrayList(); ;


	public PeopleBS(JdbcTemplateEx jdbcTemplate) {
		hr = new HrDao();
		sms = new SmsDao(jdbcTemplate);
		existPeople.addAll(sms.getPeopleHR());
	}
	
	public Object getEmpty(Object value) {
		if (value == null)
			return "";
		return value;
	}
	
	Object[] changeHR2SMS(List list) {

		Map deptMap = new HashMap();
		Map areaMap = new HashMap();

		for (int i = 0; i < list.size(); i++) {
			Map map = (Map) list.get(i);

			String HRDEPTID = (String) map.get("HRDEPTID");

			deptMap.put(HRDEPTID, map.get("SMSDEPTID"));
			areaMap.put(HRDEPTID, map.get("AREA"));
		}

		return new Object[] { deptMap, areaMap };
	}
	
	public void execute() {
		
		
		sms.setSTATUS();
		
		
		List mappingDept = sms.getDeptHR2SMS();
		Object[] objs = changeHR2SMS(mappingDept);
		Map deptMap = (Map) objs[0];
		Map areaMap = (Map) objs[1];
		
		
		
		
		List hrList = hr.getHR();
		
		for (int i = 0; i < hrList.size(); i++) {
			Map HRMap = (Map) hrList.get(i);
			
			String HRID = (String) HRMap.get("A01101");
			
			String i_dwdm = (String) HRMap.get("I_DWDM");//生产单位
			String DEPTID = (String) HRMap.get("A01102");//行政单位
			if (DEPTID == null || DEPTID.equals("")) {
				DEPTID = (String) HRMap.get("B0100");//上级单位
			}
			if (!(i_dwdm == null || i_dwdm.equals(""))){
				DEPTID = i_dwdm;
			}
			DEPTID = this.getEmpty(deptMap.get(DEPTID)).toString();
			
			String AREA = this.getEmpty(areaMap.get((String) HRMap.get("A01102"))).toString();
			
			
			Map map = createMap(HRMap);
			
			map.put("DEPTID", DEPTID);
			map.put("AREA", AREA);
			map.put("PEOPLE_RANGE", "1");//国航
			
			map = student(map, 
					HRMap.get("A01102")==null?"":HRMap.get("A01102").toString(), 
					HRMap.get("A01190")==null?"":HRMap.get("A01190").toString(), 
					HRMap.get("A01151")==null?"":HRMap.get("A01151").toString()
					);
			
			
			
			if (existPeople.contains(HRID)){
				sms.updatePeople(map);
			}else{
				if(!(DEPTID == null || "".equals(DEPTID.trim()))){
					sms.insertPeople(map);
				}
			}
			
		}
		
	}
	
	public Map student(Map map, String A01102, String A01190, String A01151) {
		
		map.put("IS_STUDENT", "0");
		
		
		
		if(A01102.equals("10450725")){//在校学员单位
			map.put("IS_STUDENT", "1");
			map.put("STUSORT", "2");//养成生
			if("301".equals(A01190)){
				map.put("STUSTATE", "3");//在校
			}
			if("302".equals(A01190)){
				map.put("STUSTATE", "1");//实习
			}
		}
			
		if(A01102.equals("10450201")){//中国国际航空股份有限公司培训部飞行训练大队飞行学员
			map.put("IS_STUDENT", "1");
			map.put("STUSORT", "1");//大改驾
		}
		
		
		
		if("10454200".equals(A01102) && "7E".equals(A01151)){//国货航 学员
			map.put("IS_STUDENT", "1");
			map.put("STUSORT", "2");//养成生
			if("301".equals(A01190)){
				map.put("STUSTATE", "3");//在校
			}
			if("302".equals(A01190)){
				map.put("STUSTATE", "1");//实习
			}
		}
		
		return map;
	}
	
	public Map createMap(Map HRMap) {
		MapBean cm = new MapBean();
		
		if (existPeople.contains(HRMap.get("A01101").toString())){
			cm.map = sms.getPeople(HRMap.get("A01101").toString());
		}else{
			cm.put("ID", UUID.randomUUID().toString());
		}
		
		
		cm.put("HRID", HRMap.get("A01101"));
		
		cm.put("NAME", HRMap.get("A0101"));// 姓名
		cm.put("FIRST_NAME", HRMap.get("FIRSTNAME"));
		cm.put("MID_NAME", HRMap.get("MIDNAME"));
		cm.put("LAST_NAME", HRMap.get("LASTNAME"));
		cm.put("NAME_SPELL", HRMap.get("A01148"));
		
		cm.put("NATION", HRMap.get("B_CODENAME"));// 民族
		
		cm.put("SEX", HRMap.get("A_CODENAME"));// 性别
		
		cm.put("IDCARD", HRMap.get("A0177"));// 身份证号
		
		cm.putDate("BIRTHDAY", HRMap.get("A0111"));// 出生年月
		
		cm.putDate("WORK_DATE", HRMap.get("A0141"));// 参加工作时间
		
		cm.putDate("SYSTEM_WORKDATE", HRMap.get("A0144"));// 进入民航系统时间
		
		cm.putDate("JOIN_CADATE", HRMap.get("A01149"));// 进入国航时间
		
		cm.put("PROFESSIONAL", HRMap.get("O_CODENAME"));// 人员职级
		
		cm.put("NATIONALITY", HRMap.get("F_CODENAME"));// 国籍
		
		
		
		String STATUS = status((String)HRMap.get("G_CODENAME"));
		cm.put("STATUS", STATUS);// 当前状态
		
		
		
		String SORT = sort((String) HRMap.get("M_CODENAME"),
				(String) HRMap.get("D_CODENAME"), 
				(String) HRMap.get("A01102"));
		cm.put("SORT", SORT);// 人员类型

		

		return cm.get();

	}
	
	public String status(String G_CODENAME) {
		if (G_CODENAME == null)
			return PeopleConstant.STATUS_NOTKNOW;
		else if (G_CODENAME.equals("正常在岗"))
			return PeopleConstant.STATUS_ACTIVE;
		else if (G_CODENAME.equals("退休"))
			return PeopleConstant.STATUS_OUT;
		return PeopleConstant.STATUS_NOTKNOW;
	}
	
	public String sort(String m_codename, String d_codename, String A01102) {
		if (m_codename == null) {
			if (d_codename != null) {
				if (d_codename.indexOf("飞行") != -1)
					return PeopleConstant.SORT_P;
				else if (d_codename.indexOf("空警") != -1)
					return PeopleConstant.SORT_KJ;
			} else if (A01102 != null) {
				if (A01102.equals("10450725") || A01102.equals("10450201"))//飞行学员单位
					return PeopleConstant.SORT_P;
				else if (A01102.equals("10451553"))//乘务学员单位
					return PeopleConstant.SORT_C;
			}
		} else {
			if (m_codename.indexOf("飞行") != -1)
				return PeopleConstant.SORT_P;
			else if (m_codename.indexOf("乘务") != -1)
				return PeopleConstant.SORT_C;
			else if (m_codename.indexOf("地勤") != -1)
				return PeopleConstant.SORT_D;
			else if (m_codename.indexOf("空警") != -1)
				return PeopleConstant.SORT_KJ;
			else if (m_codename.indexOf("保卫员") != -1)
				return PeopleConstant.SORT_KB;
			else if (m_codename.indexOf("机械") != -1)
				return PeopleConstant.SORT_P;
			else if (m_codename.indexOf("通信") != -1)
				return PeopleConstant.SORT_P;
			else if (m_codename.indexOf("领航员") != -1)
				return PeopleConstant.SORT_P;
			else if (m_codename.equals("实习")) {
				if (d_codename != null) {
					if (d_codename.indexOf("飞行") != -1)
						return PeopleConstant.SORT_P;
					else if (d_codename.indexOf("空警") != -1)
						return PeopleConstant.SORT_KJ;
				}
			} else if (m_codename.equals("在校")) {
				if (d_codename != null) {
					if (d_codename.indexOf("飞行") != -1)
						return PeopleConstant.SORT_P;
				}
			}
		}
		if (A01102 != null) {
			if (A01102.equals("10450725") || A01102.equals("10450201"))// 飞行学员单位
				return PeopleConstant.SORT_P;
			else if (A01102.equals("10451553"))// 乘务学员单位
				return PeopleConstant.SORT_C;
		}
		return PeopleConstant.SORT_OTHER;
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new PeopleBS(JdbcTemplateForm.getJdbcTemplateSMS()).execute();
		
	}

	
	
private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
class MapBean {
	Map map = new HashMap();

	public void put(String key, Object value) {
		if (value == null)
			map.put(key, "");
		else
			map.put(key, value.toString().trim());
	}

	public void putDate(String key, Object value) {
		if (value == null)
			map.put(key, "");
		else {
			try {
				map.put(key, df.parse(value.toString()));
			} catch (ParseException e) {
				map.put(key, "");
			}
		}
	}

	public Map get() {
		return map;
	}

}
	
	
	
	
}
