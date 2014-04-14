package com.sms.training.qualification.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.common.util.DateTool;
import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.dao.ITfQualifiedDeclareDao;
@Repository("tfQualifiedDeclareDao")
@Transactional
public class TfQualifiedDeclareDao extends PublicDao implements ITfQualifiedDeclareDao{

	
	@Override
	public List getPilotList(String orgid, String pilottechgrade) {
		String sql = "select p.hrid,p.name,p.sex , p.idcard,p.deptid,p.sex atrid,p.sex ptgradeid from cm_people p where p.deptid='8a8a11192ffc6533012ffccfc8860008'";
		return this.findBySQL(sql);
	}
	public void deletePilotByTypeid(String typeid){
		String hql = "delete from TfQualDeclaraPilotStay t where t.tfQualBaseType.typeid='"+typeid+"' and t.status is null";
		this.updateStation(hql);
	}

	@Override
	public List<String> getPilotListByOrgid(String atrid, String grade, String orgid,TfQualBaseAccessconditions tfQualBaseAccessconditions) {
		StringBuffer sqlBuffer = new StringBuffer(2000);
		sqlBuffer.append("select c.id");
		sqlBuffer.append(" from cm_people c,tf_qual_pilot_licence l, tf_qual_pilot_techrecord t,tf_qual_base_pilot_info i");
		sqlBuffer.append(" where c.id = l.pilotid and l.plcseq = t.plcseq  and c.id = i.pilotid  and l.plcstus != '1' and t.ptrcurrent != '1'");
	 	sqlBuffer.append(" and c.id not in (select s.pilotid from tf_qual_declara_pilot_stay s where s.typeid = '").append(tfQualBaseAccessconditions.getTfQualBaseType().getTypeid()).append("' and s.status is not null)");
		sqlBuffer.append(" and l.atrid = '").append(atrid).append("'");
		sqlBuffer.append(" and t.ptgradeid = '").append(grade).append("'");
		//sqlBuffer.append(" and c.deptid in ( select o.id from sys_organization o start with o.id='").append("8a8a11ef3183b73d01318456e2a20000").append("' connect by prior o.id  = o.parent_fun_id )");
		if(!orgid.equals("1"))
		{
			sqlBuffer.append(" and c.deptid in " +orgid);
		}
		//判断飞行经历时间
		if(tfQualBaseAccessconditions.getSvprevlevelgrade()!=null){
			if(tfQualBaseAccessconditions.getSvallflytimemin() != null && tfQualBaseAccessconditions.getSvallflytimemax() == null ){
				sqlBuffer.append(" and i.dutytime/60 >= ").append(tfQualBaseAccessconditions.getSvallflytimemin());
			}else if(tfQualBaseAccessconditions.getSvallflytimemin() != null && tfQualBaseAccessconditions.getSvallflytimemax() != null ){
				sqlBuffer.append(" and i.dutytime/60 >= ").append(tfQualBaseAccessconditions.getSvallflytimemin());
				sqlBuffer.append(" and i.dutytime/60 < ").append(tfQualBaseAccessconditions.getSvallflytimemax());
			} else if (tfQualBaseAccessconditions.getSvallflytimemax() != null  && tfQualBaseAccessconditions.getSvallflytimemin() == null) {
				sqlBuffer.append(" and i.dutytime/60 < ").append(tfQualBaseAccessconditions.getSvallflytimemax());
			}
			//判断操作驾驶员起落次数
			if(tfQualBaseAccessconditions.getSvupdownmin() != null && tfQualBaseAccessconditions.getSvupdownmax() == null ){
				sqlBuffer.append(" and i.updown >= ").append(tfQualBaseAccessconditions.getSvupdownmin());
			}else if(tfQualBaseAccessconditions.getSvupdownmin() != null && tfQualBaseAccessconditions.getSvupdownmax() != null ){
				sqlBuffer.append(" and i.updown >= ").append(tfQualBaseAccessconditions.getSvupdownmax());
				sqlBuffer.append(" and i.updown < ").append(tfQualBaseAccessconditions.getSvallflytimemax());
			} else if (tfQualBaseAccessconditions.getSvupdownmax() != null  && tfQualBaseAccessconditions.getSvupdownmin() == null) {
				sqlBuffer.append(" and i.updown < ").append(tfQualBaseAccessconditions.getSvupdownmax());
			}
			if(tfQualBaseAccessconditions.getSvcurrentflytime() != null){
				sqlBuffer.append(" and i.currentflytime/60 >=").append(tfQualBaseAccessconditions.getSvcurrentflytime());
			}
			if(tfQualBaseAccessconditions.getSvcurrentupdown() != null){
				sqlBuffer.append(" and i.currentupdown >=").append(tfQualBaseAccessconditions.getSvcurrentupdown());
			}
			//判断
			sqlBuffer.append(" and ");
			String grades = tfQualBaseAccessconditions.getSvprevlevelgrade().replace(",", "','");
			String  sqlWhere = "i.prevlevelgrade in ('"+grades+"')";
			if(tfQualBaseAccessconditions.getSvprevlevelgrade().indexOf("n")>=0){
			   sqlBuffer.append("  ( i.prevlevelgrade is null ");
			   sqlBuffer.append(" or ").append(sqlWhere).append(")");
			}else{
				sqlBuffer.append(sqlWhere);
			}
			//机长飞行经历
			if(tfQualBaseAccessconditions.getSvcapflytime()!= null && !tfQualBaseAccessconditions.getSvcapflytime().equals("")){
				sqlBuffer.append(" and i.capflytime >= ").append(tfQualBaseAccessconditions.getSvcapflytime());
			}
			//2年飞行经历
			if(tfQualBaseAccessconditions.getSvflytimeyear2()!= null && !tfQualBaseAccessconditions.getSvflytimeyear2().equals("")){
				sqlBuffer.append(" and i.flytimeyear2 >= ").append(tfQualBaseAccessconditions.getSvflytimeyear2());
			}
			//12月内飞行经历
			if(tfQualBaseAccessconditions.getSvflytimemonth12()!= null && !tfQualBaseAccessconditions.getSvflytimemonth12().equals("")){
				sqlBuffer.append(" and i.flytimemonth12 >= ").append(tfQualBaseAccessconditions.getSvflytimemonth12());
			}
			//6月内飞行经历
			if(tfQualBaseAccessconditions.getSvflytimemonth6()!= null && !tfQualBaseAccessconditions.getSvflytimemonth6().equals("")){
				sqlBuffer.append(" and i.flytimemonth6 >= ").append(tfQualBaseAccessconditions.getSvflytimemonth6());
			}
			//总起落数
			if(tfQualBaseAccessconditions.getSvallupdown()!= null && !tfQualBaseAccessconditions.getSvallupdown().equals("")){
				sqlBuffer.append(" and i.allupdown >= ").append(tfQualBaseAccessconditions.getSvallupdown());
			}
			//12个月起落
			if(tfQualBaseAccessconditions.getSvupdownmonth12()!= null && !tfQualBaseAccessconditions.getSvupdownmonth12().equals("")){
				sqlBuffer.append(" and i.updownmonth12 >= ").append(tfQualBaseAccessconditions.getSvupdownmonth12());
			}
			//90天起落
			if(tfQualBaseAccessconditions.getSvupdownday90()!= null && !tfQualBaseAccessconditions.getSvupdownday90().equals("")){
				sqlBuffer.append(" and i.updownday90 >= ").append(tfQualBaseAccessconditions.getSvupdownday90());
			}
			//ATPL:Y有
			if(tfQualBaseAccessconditions.getSvatpl()!= null && !tfQualBaseAccessconditions.getSvatpl().equals("")){
				sqlBuffer.append(" and i.atpl ='Y'");
			}
			//多发Y代表有
			if(tfQualBaseAccessconditions.getSvmultiplecertificate()!= null && !tfQualBaseAccessconditions.getSvmultiplecertificate().equals("")){
				sqlBuffer.append(" and i.multiplecertificate ='Y'");
			}
			//判断执照类型  默认是商照 2是航线
//			if(tfQualBaseAccessconditions.getSvlicensetype() != null && !tfQualBaseAccessconditions.getSvlicensetype().equals("")){
//				sqlBuffer.append(" and l.plclifecycle ='").append(tfQualBaseAccessconditions.getSvlicensetype()).append("'");
//			}
			if(tfQualBaseAccessconditions.getSvlicensetype() != null ){
				if(tfQualBaseAccessconditions.getSvlicensetype().equals("") || tfQualBaseAccessconditions.getSvlicensetype().equals("1"))
				{
					sqlBuffer.append(" and ( l.plclifecycle ='1' or l.plclifecycle is null) ");
				}else if(tfQualBaseAccessconditions.getSvlicensetype().equals("2"))
				{
					sqlBuffer.append(" and l.plclifecycle ='").append(tfQualBaseAccessconditions.getSvlicensetype()).append("'");
				}
			}
			//判断条件中ICAO等级G3代表大于等于3级G4代表大于等于4级
			if(tfQualBaseAccessconditions.getSvicao() != null && tfQualBaseAccessconditions.getSvicao().equals("G3")){
				sqlBuffer.append(" and i.icao >=3");
			}else if(tfQualBaseAccessconditions.getSvicao() != null && tfQualBaseAccessconditions.getSvicao().equals("G4")){
				sqlBuffer.append(" and i.icao >=4");
			}
//			//计算一般应急日期当前时间有效的(有效期一年)
//			Date  nowdate = null;
//			nowdate = DateTool.addMonths(-12);
//			sqlBuffer.append(" and i.rlastdate >= to_date('").append(DateTool.formatDate(nowdate, DateTool.DEFAULT_DATE_FORMAT)).append("','yyyy-mm-dd')");
//			//计算危险品有效的(暂时不考虑货航人员货航有效期一年，其它人员二年)
//			nowdate = DateTool.addMonths(-24);
// 			sqlBuffer.append(" and i.dglastdate >= to_date('").append(DateTool.formatDate(nowdate, DateTool.DEFAULT_DATE_FORMAT)).append("','yyyy-mm-dd')");
//			//计算体检合格证有效的(有效期一年)
// 			nowdate = DateTool.addMonths(-12);
//		 	sqlBuffer.append("and i.medicalcertificate >= to_date('").append(DateTool.formatDate(nowdate, DateTool.DEFAULT_DATE_FORMAT)).append("','yyyy-mm-dd')");
//			//计算执照有效的(有效期6个月)
//			nowdate = DateTool.addMonths(-6);
//			sqlBuffer.append(" and l.plccheckd >= to_date('").append(DateTool.formatDate(nowdate, DateTool.DEFAULT_DATE_FORMAT)).append("','yyyy-mm-dd')");
		} 
		return this.findBySQL(sqlBuffer.toString());
	}
	@Override
	public List<Object> getTypeCount(String orgid) {
		StringBuffer sqlBuffer = new StringBuffer(1000);
		sqlBuffer.append("select t.qtgroupid||'_'||t.originalgrade  qtgroupid, '('|| count(*) ||')'");
		sqlBuffer.append(" from tf_qual_base_type t, tf_qual_declara_pilot_stay s, cm_people c");
		sqlBuffer.append(" where t.typeid = s.typeid  and s.pilotid = c.id");
		sqlBuffer.append(" and c.deptid in ( select o.id from sys_organization o start with o.id='").append(orgid).append("' connect by prior o.id  = o.parent_fun_id )");		
		sqlBuffer.append(" and s.status is null  group by t.qtgroupid,t.originalgrade");
		return this.findBySQL(sqlBuffer.toString());
	}
	
	
}  
