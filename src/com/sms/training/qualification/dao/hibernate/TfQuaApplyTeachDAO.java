package com.sms.training.qualification.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.common.util.DateTool;
import com.sinoframe.dao.hibernate.PublicDao;
import com.sms.training.qualification.dao.ITfQuaApplyTeachDAO;
@Repository("tfQuaApplyTeachDAO")
@Transactional
public class TfQuaApplyTeachDAO extends PublicDao implements ITfQuaApplyTeachDAO {

	@Override
	public List<Object> findPilotList(String atrId, String orgIds, Date trainDate,String orgName,String subGroupId) {
		String date = DateTool.formatDate(trainDate, "yyyy-MM");
		StringBuffer sqlBuffer = new StringBuffer(2000);
		sqlBuffer.append("select lc.pilotid ,");
		if("QUAL_FT_FXJYLLKFX".equals(subGroupId) && orgName.contains("天津")){
			sqlBuffer.append(" case "+
				" when months_between(to_date('").append(date).append("','yyyy-mm') , lc.plccheckd )=5 then 'BX' "+
				" when months_between(to_date('").append(date).append("','yyyy-mm') , lc.plccheckd )=4 then 'YX' "+
				" else '' "+
				" end isMust ");
		}else{
			sqlBuffer.append(" case "+
				" when months_between(to_date('").append(date).append("','yyyy-mm') , lc.plccheckd )=7 then 'BX' "+
				" when months_between(to_date('").append(date).append("','yyyy-mm') , lc.plccheckd )=6 then 'YX' "+
				" else '' "+
				" end isMust ");
		}
		sqlBuffer.append("from tf_qual_pilot_licence lc ,tf_qual_pilot_techrecord g ,cm_people cp  ");
		sqlBuffer.append("where lc.plcseq = g.plcseq and cp.id=lc.pilotid and lc.plcstus='0' and g.ptrcurrent='0' ");
		sqlBuffer.append(" and g.ptgradeid in ('TA','TB','TC') ");
		//此处待修改
		sqlBuffer.append(" and lc.pilotid not in (select st.pilotid from tf_qual_declara_pilot_stay st where to_char(st.year ,'YYYY-MM') " +
				" in (to_char(to_date('"+date+"','YYYY-MM') + interval '1' month ,'YYYY-MM'),'"+date+"'))");
		if(!atrId.equals(""))
		{
			sqlBuffer.append(" and lc.atrid='"+atrId+"'");
		}
		if(!orgIds.equals(""))
		{
			sqlBuffer.append(" and cp.deptid in " +orgIds);
		}
		sqlBuffer.append(" and to_char(lc.plccheckd ,'YYYY-MM') in ");
		if("QUAL_FT_FXJYLLKFX".equals(subGroupId) && orgName.contains("天津")){
			sqlBuffer.append(" (to_char(to_date('"+date+"','YYYY-MM') - interval '4' month ,'YYYY-MM'),to_char(to_date('"+date+"','YYYY-MM') - interval '5' month ,'YYYY-MM'))");
		}else{
			sqlBuffer.append(" (to_char(to_date('"+date+"','YYYY-MM') - interval '6' month ,'YYYY-MM'),to_char(to_date('"+date+"','YYYY-MM') - interval '7' month ,'YYYY-MM'))");
		}
//		sqlBuffer.append(" order by lc.plccheckd ");
		return this.findBySQL(sqlBuffer.toString());
	}

}
