package com.sms.data.hr.people;

import java.util.List;
import java.util.Map;

import org.pentaho.di.core.exception.KettleException;


import com.sinoframe.common.util.DateTool;
import com.sms.data.jdbc.JdbcTemplateEx;
import com.sms.data.jdbc.JdbcTemplateForm;
import com.sms.data.kettle.Kettle;

public class SyncPeolpe {
	
	private JdbcTemplateEx jdbcTemplateHR = JdbcTemplateForm.getJdbcTemplateHR();
	private JdbcTemplateEx jdbcTemplateAH = JdbcTemplateForm.getJdbcTemplateSMS();
	private JdbcTemplateEx jdbcTemplateOA = JdbcTemplateForm.getJdbcTemplateOA();
	private JdbcTemplateEx jdbcTemplateAHDB = JdbcTemplateForm.getJdbcTemplateAHDB();
	private JdbcTemplateEx jdbcTemplateRZ = JdbcTemplateForm.getJdbcTemplateRZ();
	private JdbcTemplateEx jdbcTemplateTJ = JdbcTemplateForm.getJdbcTemplateTJ();
	private JdbcTemplateEx jdbcTemplateFK = JdbcTemplateForm.getJdbcTemplateFK();
	
	public void auto() {
		try{
		
		System.out.println("开始时间："+DateTool.getNow());
		//清空ah_tu_hr临时表
		jdbcTemplateAH.execute("delete from ah_tu_hr");
		System.out.println("清空ah_tu_hr表，完成");
		//抽取数据
		String sql = "select " +
				"sys_guid() USERUUID," +
				"psncode USERNAME," +
				"'14e1b600b1fd579f47433b88e8d85291' PASSWORD," +
				"psncode HRID," +
				"t.oldpsncode oldcode," +
				"psnname NAME," +
				"id IDCARD," +
				"EMAIL," +
				"UNITNAME," +
				"DEPTNAME," +
				"'HR' USERTYPE," +
				"MOBILE" +
				" from ahic.bd_psn_view  t" +
				" where not exists (select * from " +
				"(select psncode from AHIC.BD_PSN_VIEW t group by t.psncode having count(*)>1) a" +
				" where a.psncode=t.psncode)" +
				"and t.psncode is not null and t.oldpsncode is not null";
		List hrList = jdbcTemplateHR.queryForList(sql);
		System.out.println(hrList.size());
		for (int i = 0; i < hrList.size(); i++) {
			Map HRMap = (Map) hrList.get(i);
			//System.out.println(HRMap);
			jdbcTemplateAH.insertMap(HRMap, "ah_tu_hr");
		}
		System.out.println("抽取数据，完成");
		//插入ah_tu_hr_bak全量数据
		String betsql = "insert into ah_tu_hr_bak select t.useruuid,t.username,t.password,t.hrid,t.name,t.idcard,t.unitname,t.deptname,t.usertype,t.email,t.oldcode, sysdate TRSDATE,t.mobile from ah_tu_hr t";
		jdbcTemplateAH.execute(betsql);
		System.out.println("插入ah_tu_hr_bak全量数据，完成");
		//更新ah_user中邮箱和电话信息
		String UPsql = "update ah_user t set (t.email,t.mobile)=(select a.email,a.mobile from ah_tu_hr a where a.hrid =t.hrid) where t.hrid in (select hrid from ah_tu_hr)";
		jdbcTemplateAH.execute(UPsql);
		System.out.println("更新ah_user中邮箱和电话信息，完成");
		//删除ah_tu_hr中已有员工数据
		String desql = "delete from ah_tu_hr t where t.hrid in (select hrid from ah_user)";
		jdbcTemplateAH.execute(desql);
		System.out.println("删除ah_tu_hr中已有员工数据，完成");
		//插入新增加员工数据
		String INsql= "insert into ah_user select t.useruuid,t.username,t.password,t.hrid,t.name,t.idcard,t.unitname,t.deptname,t.usertype,t.email,t.oldcode,t.mobile from ah_tu_hr t";
		jdbcTemplateAH.execute(INsql);
		System.out.println("插入新增加员工数据，完成");
		//插入ah_peruser表人力系统信息
		String PERsql= "insert into ah_peruser select t.useruuid peruuid,'04' sysid,t.hrid login,null plaincode,null password,t.name,t.idcard,t.unitname,t.deptname,null ignore from ah_tu_hr t";
		jdbcTemplateAH.execute(PERsql);
		System.out.println("插入ah_peruser表人力系统信息，完成");
		//插入ah_mapping表人力系统权限
		String MAPsql= "insert into ah_mapping select t.useruuid uuid,'04' sysid,t.useruuid,t.useruuid perid,'1' mflag from ah_tu_hr t";
		jdbcTemplateAH.execute(MAPsql);
		System.out.println("插入ah_mapping表人力系统权限，完成");
		//删除ah_psn_old数据
		jdbcTemplateAH.execute("delete from ah_psn_old");
		System.out.println("删除ah_psn_old数据，完成");
		//抽取离职员工数据
		String sql2 = "select t.psncode,t.psnname,t.id, t.PSNCLSCOPENAME,t.PSNCLSCOPE from ahic.bd_psn_old_view t";
		List hrDLList = jdbcTemplateHR.queryForList(sql2);
		//System.out.println(hrDLList.size());
		for (int i = 0; i < hrDLList.size(); i++) {
			Map HRDLMap = (Map) hrDLList.get(i);
			//System.out.println(HRDLMap);
			jdbcTemplateAH.insertMap(HRDLMap, "ah_psn_old");
		}
		System.out.println("抽取离职员工数据，完成");
		System.out.println("结束时间："+DateTool.getNow());
		
		
		System.out.println("同步密码，开始时间："+DateTool.getNow());
		
		//清空ah_tu_oa临时表
		jdbcTemplateAH.execute("delete from ah_tu_oa");
		System.out.println("清空ah_tu_oa表，完成");
		//抽取oa系统数据
		String OAsql = "select t.loginid,t.username,t.departmentname, t.password from View_OALoginInfo t";
		List oaList = jdbcTemplateOA.queryForList(OAsql);
		System.out.println("OA人数："+oaList.size());
		for (int i = 0; i < oaList.size(); i++) {
			Map OAMap = (Map) oaList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(OAMap, "ah_tu_oa");
		}
		System.out.println("抽取oa系统数据，完成");
		//更新ah_peruser中oa系统的用户密码
		String UPOAsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_oa a where a.loginid =t.login  ) where t.login in (select loginid from ah_tu_oa) and t.sysid = '01' and  t.login not in (select a.loginid from ah_tu_oa a group by  a.loginid having count(*)>1)";
		jdbcTemplateAH.execute(UPOAsql);
		System.out.println("更新ah_peruser中oa系统的用户密码，完成");
		
		
		//清空ah_tu_zkr临时表
		jdbcTemplateAH.execute("delete from ah_tu_zkr");
		System.out.println("清空ah_tu_zkr表，完成");
		//抽取核心系统数据
		String HXsql = "select u.人员编码 USERUUID,u.姓名 USERNAME,u.登陆密码 PASSWORD,u.部门名称 DEPTNAME,u.系统权限 SYSTEM from V_USER u";
		List HXList = jdbcTemplateAHDB.queryForList(HXsql);
		System.out.println("核心人数："+HXList.size());
		for (int i = 0; i < HXList.size(); i++) {
			Map HXMap = (Map) HXList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(HXMap, "ah_tu_zkr");
		}
		System.out.println("抽取核心系统数据，完成");
		//更新ah_peruser中核心系统的用户密码
		String UPHXsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_zkr a where a.useruuid =t.login  ) where t.login in (select useruuid from ah_tu_zkr) and t.sysid = '06' and  t.login not in (select a.useruuid from ah_tu_zkr a group by  a.useruuid having count(*)>1)";
		jdbcTemplateAH.execute(UPHXsql);
		System.out.println("更新ah_peruser中核心系统的用户密码，完成");
		//更新ah_peruser中再保分入系统的用户密码
		String UPFRsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_zkr a where a.useruuid =t.login  ) where t.login in (select useruuid from ah_tu_zkr) and t.sysid = '17' and  t.login not in (select a.useruuid from ah_tu_zkr a group by  a.useruuid having count(*)>1)";
		jdbcTemplateAH.execute(UPFRsql);
		System.out.println("更新ah_peruser中再保分入系统的用户密码，完成");
		//更新ah_peruser中再保分出系统的用户密码
		String UPFCsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_zkr a where a.useruuid =t.login  ) where t.login in (select useruuid from ah_tu_zkr) and t.sysid = '18' and  t.login not in (select a.useruuid from ah_tu_zkr a group by  a.useruuid having count(*)>1)";
		jdbcTemplateAH.execute(UPFCsql);
		System.out.println("更新ah_peruser中再保分出系统的用户密码，完成");
		
		//清空ah_tu_zkr临时表
		jdbcTemplateAH.execute("delete from ah_tu_zkr");
		System.out.println("清空ah_tu_zkr表，完成");
		//抽取单证系统数据
		String DZsql = "select u.人员编码 USERUUID,u.姓名 USERNAME,u.登陆密码 PASSWORD,u.部门名称 DEPTNAME,u.系统权限 SYSTEM from v_vslevel u";
		List DZList = jdbcTemplateAHDB.queryForList(DZsql);
		System.out.println("单证人数："+DZList.size());
		for (int i = 0; i < DZList.size(); i++) {
			Map DZMap = (Map) DZList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(DZMap, "ah_tu_zkr");
		}
		System.out.println("抽取单证系统数据，完成");
		//更新ah_peruser中单证系统的用户密码
		String UPDZsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_zkr a where a.useruuid =t.login  ) where t.login in (select useruuid from ah_tu_zkr) and t.sysid = '12' and  t.login not in (select a.useruuid from ah_tu_zkr a group by  a.useruuid having count(*)>1)";
		jdbcTemplateAH.execute(UPDZsql);
		System.out.println("更新ah_peruser中单证系统的用户密码，完成");
		
		//清空ah_tu_zkr临时表
		jdbcTemplateAH.execute("delete from ah_tu_zkr");
		System.out.println("清空ah_tu_zkr表，完成");
		//抽取核保核赔系统数据
		String HBsql = "select u.人员编码 USERUUID,u.姓名 USERNAME,u.登陆密码 PASSWORD,u.部门名称 DEPTNAME,u.系统权限 SYSTEM from v_uwgrade u";
		List HBList = jdbcTemplateAHDB.queryForList(HBsql);
		System.out.println("核保核赔人数："+HBList.size());
		for (int i = 0; i < HBList.size(); i++) {
			Map HBMap = (Map) HBList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(HBMap, "ah_tu_zkr");
		}
		System.out.println("抽取核保核赔系统数据，完成");
		//更新ah_peruser中核保核赔系统的用户密码
		String UPHBsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_zkr a where a.useruuid =t.login  ) where t.login in (select useruuid from ah_tu_zkr) and t.sysid = '15' and  t.login not in (select a.useruuid from ah_tu_zkr a group by  a.useruuid having count(*)>1)";
		jdbcTemplateAH.execute(UPHBsql);
		System.out.println("更新ah_peruser中核保核赔系统的用户密码，完成");
		
		
		//清空ah_tu_xiaoguan临时表
		jdbcTemplateAH.execute("delete from ah_tu_xiaoguan");
		System.out.println("清空ah_tu_xiaoguan表，完成");
		//抽取销管系统数据
		String XGsql = "select s.usercode,s.password,s.username from sales_view_users s";
		List XGList = jdbcTemplateAHDB.queryForList(XGsql);
		System.out.println("销管人数："+XGList.size());
		for (int i = 0; i < XGList.size(); i++) {
			Map XGMap = (Map) XGList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(XGMap, "ah_tu_xiaoguan");
		}
		System.out.println("抽取销管系统数据，完成");
		//更新ah_peruser中销管系统的用户密码
		String UPXGsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_xiaoguan a where a.usercode =t.login  ) where t.login in (select usercode from ah_tu_xiaoguan) and t.sysid = '13' and  t.login not in (select a.usercode from ah_tu_xiaoguan a group by  a.usercode having count(*)>1)";
		jdbcTemplateAH.execute(UPXGsql);
		System.out.println("更新ah_peruser中销管系统的用户密码，完成");
		
		
		//清空ah_tu_rizhi临时表
		jdbcTemplateAH.execute("delete from ah_tu_rizhi");
		System.out.println("清空ah_tu_rizhi表，完成");
		//抽取销管系统数据
		String RZsql = "select u.loginname,u.username,u.orgname,u.pwd from v_user u";
		List RZList = jdbcTemplateRZ.queryForList(RZsql);
		System.out.println("日志人数："+RZList.size());
		for (int i = 0; i < RZList.size(); i++) {
			Map RZMap = (Map) RZList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(RZMap, "ah_tu_rizhi");
		}
		System.out.println("抽取日志系统数据，完成");
		//更新ah_peruser中日志系统的用户密码
		String UPRZsql = "update ah_peruser t set (t.password)=(select a.pwd from ah_tu_rizhi a where a.loginname =t.login  ) where t.login in (select loginname from ah_tu_rizhi) and t.sysid = '05' and  t.login not in (select a.loginname from ah_tu_rizhi a group by  a.loginname having count(*)>1)";
		jdbcTemplateAH.execute(UPRZsql);
		System.out.println("更新ah_peruser中日志系统的用户密码，完成");
		
		//清空ah_tu_tongji临时表
		jdbcTemplateAH.execute("delete from ah_tu_tongji");
		System.out.println("清空ah_tu_tongji表，完成");
		//抽取统计系统数据
		String TJsql = "select c.USERCODE,c.PASSWORD,c.USERNAME,c.comname from vw_cc_user c";
		List TJList = jdbcTemplateTJ.queryForList(TJsql);
		System.out.println("统计人数："+TJList.size());
		for (int i = 0; i < TJList.size(); i++) {
			Map TJMap = (Map) TJList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(TJMap, "ah_tu_tongji");
		}
		System.out.println("抽取统计系统数据，完成");
		//更新ah_peruser中统计系统的用户密码
		String UPTJsql = "update ah_peruser t set (t.password)=(select a.password from ah_tu_tongji a where a.usercode =t.login  ) where t.login in (select usercode from ah_tu_tongji) and t.sysid = '03' and  t.login not in (select a.usercode from ah_tu_tongji a group by  a.usercode having count(*)>1)";
		jdbcTemplateAH.execute(UPTJsql);
		System.out.println("更新ah_peruser中统计系统的用户密码，完成");
		
		//清空ah_tu_feikong临时表
		jdbcTemplateAH.execute("delete from ah_tu_feikong");
		System.out.println("清空ah_tu_feikong表，完成");
		//抽取费控系统数据
		String FKsql = "select c.user_name,c.encrypted_user_password,c.name,c.company_short_name,c.uint_name from ahic_portal_login c";
		List FKList = jdbcTemplateFK.queryForList(FKsql);
		System.out.println("费控人数："+FKList.size());
		for (int i = 0; i < FKList.size(); i++) {
			Map FKMap = (Map) FKList.get(i);
			//System.out.println(OAMap);
			jdbcTemplateAH.insertMap(FKMap, "ah_tu_feikong");
		}
		System.out.println("抽取费控系统数据，完成");
		//更新ah_peruser中费控系统的用户密码
		String UPFKsql = "update ah_peruser t set (t.password)=(select a.encrypted_user_password from ah_tu_feikong a where a.user_name =t.login  ) where t.login in (select user_name from ah_tu_feikong) and t.sysid = '08' and  t.login not in (select a.user_name from ah_tu_feikong a group by  a.user_name having count(*)>1)";
		jdbcTemplateAH.execute(UPFKsql);
		System.out.println("更新ah_peruser中费控系统的用户密码，完成");
		
		System.out.println("同步密码，结束时间："+DateTool.getNow());
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
	}
	
	public void rizhi() {
		try{
			
		System.out.println("同步日志开始时间："+DateTool.getNow());		
		//抽取各系统审计日志数据
		//清空ah_tu_logdate临时表
		System.out.println("抽取各系统审计日志数据，开始");
		jdbcTemplateAH.execute("TRUNCATE TABLE ah_tu_logdate");
		System.out.println("清空ah_tu_logdate表，完成");
		//抽取费控系统日志数据
		String SJRZsql = "select distinct t.user_code||'-FK-'||rownum loguuid, t.user_code useruuid,t.user_name username,null micip,'费控系统' SYSNAME,to_char(t.operation_time,'yyyy-MM-dd hh24:mi:ss') optime,t.operation_name||t.description opname from ahic_document_log_vl t where  to_char(t.operation_time, 'yyyy-mm-dd') > '2013-09-01'";
		List SJRZList = jdbcTemplateFK.queryForList(SJRZsql);
		System.out.println("费控日志数据数："+SJRZList.size());
		for (int i = 0; i < SJRZList.size(); i++) {
			Map SJRZMap = (Map) SJRZList.get(i);
			jdbcTemplateAH.insertMap(SJRZMap, "ah_tu_logdate");
		}
		System.out.println("抽取费控日志系统数据，完成");
		//抽取日志系统日志数据
	    SJRZsql = "select t.USERUUID||'-RZ-'||rownum loguuid,t.USERUUID,t.USERNAME,t.MICIP,'日志系统' SYSNAME,t.OPTIME,t.OPNAME from v_log t where t.OPTIME > '2013-09-01%'";
		SJRZList = jdbcTemplateRZ.queryForList(SJRZsql);
		System.out.println("日志日志数据数："+SJRZList.size());
		for (int i = 0; i < SJRZList.size(); i++) {
			Map SJRZMap = (Map) SJRZList.get(i);
			jdbcTemplateAH.insertMap(SJRZMap, "ah_tu_logdate");
		}
		System.out.println("抽取日志日志系统数据，完成");
		//抽取HR系统日志数据
	    SJRZsql = "select t.USERNAME||'-HR-'||rownum loguuid,t.USERUUID,t.USERNAME,t.MICIP,'HR系统'SYSNAME,t.OPTIME,t.OPNAME from ahic.operatelog_view t where t.OPTIME > '2013-09-01%'";
		SJRZList = jdbcTemplateHR.queryForList(SJRZsql);
		System.out.println("HR日志数据数："+SJRZList.size());
		for (int i = 0; i < SJRZList.size(); i++) {
			Map SJRZMap = (Map) SJRZList.get(i);
			jdbcTemplateAH.insertMap(SJRZMap, "ah_tu_logdate");
		}
		System.out.println("抽取OA日志系统数据，完成");
		//抽取HR系统日志数据
	    SJRZsql = "select distinct t.OPTIME+'--'+t.USERNAME as loguuid,t.useruuid,t.username,t.micip,'办公系统' sysname,t.optime,t.opname from FOR_PORTAL_LOG t where t.OPTIME > '2013-09-01%'";
		SJRZList = jdbcTemplateOA.queryForList(SJRZsql);
		System.out.println("OA日志数据数："+SJRZList.size());
		for (int i = 0; i < SJRZList.size(); i++) {
			Map SJRZMap = (Map) SJRZList.get(i);
			jdbcTemplateAH.insertMap(SJRZMap, "ah_tu_logdate");
		}
		System.out.println("抽取OA日志系统数据，完成");
		System.out.println("抽取各系统审计日志数据，完成");
		
//		//清空ah_rbac临时表
//		jdbcTemplateAH.execute("TRUNCATE TABLE ah_rbac");
//		System.out.println("清空ah_rbac表，完成");
//		//抽取日志系统权限数据
//		String RZQXsql = "select distinct t.yhxm||'--'||rownum RBACUUID,t.dlm LOGINNAME,t.yhxm USERNAME, t.jsmc ROLENAME,t.gnzmc OPERATIONAME, '日志系统' SYSNAME from V_YH_GNZ t";
//		List RZQXList = jdbcTemplateRZ.queryForList(RZQXsql);
//		System.out.println("日志权限数量："+RZQXList.size());
//		for (int i = 0; i < RZQXList.size(); i++) {
//			Map RZQXMap = (Map) RZQXList.get(i);
//			//System.out.println(RZQXMap);
//			jdbcTemplateAH.insertMap(RZQXMap, "ah_rbac");
//		}
//		System.out.println("抽日志系统权限数据，完成");

//		
//		//抽取核心系统权限数据
//		String HXQXsql = "select distinct t.usercode||t.taskname RBACUUID,t.usercode LOGINNAME,t.username,t.groupcode ROLENAME,t.taskname OPERATIONAME,'核心系统' SYSNAME from ahic.v_prpusergrade t where t.usercode in (select u.人员编码 from V_USER u)";
//		List HXQXList = jdbcTemplateAHDB.queryForList(HXQXsql);
//		System.out.println("核心权限数量："+HXQXList.size());
//		for (int i = 0; i < HXQXList.size(); i++) {
//			Map HXQXMap = (Map) HXQXList.get(i);
//			//System.out.println(RZQXMap);
//			jdbcTemplateAH.insertMap(HXQXMap, "ah_rbac");
//		}
//		System.out.println("抽核心系统权限数据，完成");
		
		System.out.println("同步日志，结束时间："+DateTool.getNow());
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
	}
	

}
