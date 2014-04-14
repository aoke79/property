package com.sms.training.qualification.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQuaApplyGeneralDAO;
@Repository("tfQuaApplyGeneralDAO")
@Transactional
public class TfQuaApplyGeneralDAO extends PublicDao implements ITfQuaApplyGeneralDAO{
	@Override
	public List<Object> getPilotListByOrgid(String atrid,String orgid,String date,String orgName,String subGroupId) {
		StringBuffer sqlBuffer = new StringBuffer(2000);
		sqlBuffer.append(" select p.pilotid,");
		if("QUAL_YB_LLFX".equals(subGroupId) && orgName.contains("天津")){
			sqlBuffer.append(" case "+
		        " when months_between(to_date('").append(date).append("','yyyy-mm') , p.plccheckd )=5 then 'BX' "+
		        " when months_between(to_date('").append(date).append("','yyyy-mm') , p.plccheckd )=4 then 'YX' "+
		        " else '' "+
		        " end isMust ");
		}else{
			sqlBuffer.append(" case "+
		        " when months_between(to_date('").append(date).append("','yyyy-mm') , p.plccheckd )=7 then 'BX' "+
		        " when months_between(to_date('").append(date).append("','yyyy-mm') , p.plccheckd )=6 then 'YX' "+
		        " else '' "+
		        " end isMust ");
		}
		sqlBuffer.append(" from tf_qual_pilot_licence p ,tf_qual_pilot_techrecord g ,cm_people cp  ");
		sqlBuffer.append(" where p.plcseq = g.plcseq and cp.id=p.pilotid and p.plcstus='0' and g.ptrcurrent='0' ");
		sqlBuffer.append(" and g.ptgradeid in ('F1','F2','F3','F4','F5','M','J','A1','A2') ");
		//此处待修改
		sqlBuffer.append(" and p.pilotid not in (select st.pilotid from tf_qual_declara_pilot_stay st where to_char(st.year ,'YYYY-MM') " +
				" in (to_char(to_date('"+date+"','YYYY-MM') + interval '1' month ,'YYYY-MM'),'"+date+"'))");
		if(!atrid.equals(""))
		{
			sqlBuffer.append(" and p.atrid='"+atrid+"'");
		}
		if(!orgid.equals(""))
		{
			sqlBuffer.append(" and cp.deptid in " +orgid);
		}
		sqlBuffer.append(" and to_char(p.plccheckd ,'YYYY-MM') in ");
		if("QUAL_YB_LLFX".equals(subGroupId) && orgName.contains("天津")){
			sqlBuffer.append(" (to_char(to_date('"+date+"','YYYY-MM') - interval '4' month ,'YYYY-MM'),to_char(to_date('"+date+"','YYYY-MM') - interval '5' month ,'YYYY-MM'))");
		}else{
			sqlBuffer.append(" (to_char(to_date('"+date+"','YYYY-MM') - interval '6' month ,'YYYY-MM'),to_char(to_date('"+date+"','YYYY-MM') - interval '7' month ,'YYYY-MM'))");
		}
//		sqlBuffer.append(" order by p.plccheckd ");
		return this.findBySQL(sqlBuffer.toString());
	}
}
