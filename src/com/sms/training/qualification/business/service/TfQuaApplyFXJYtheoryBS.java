package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQuaApplyFXJYtheoryBS;

@Service("tfQuaApplyFXJYtheoryBS")
public class TfQuaApplyFXJYtheoryBS extends BaseBS implements ITfQuaApplyFXJYtheoryBS {

	@Override
	public List<Object> getAvailablePilotStayList(TfQualBaseType type, String orgNameStr) {
		String result=" p.id,p.name,p.idcard,r.baseAirplantype.atrdesc,r.ptgradeid,r.ptrfltexptimetotaltotal ";
		String sql=genHqlStr(result,orgNameStr, type).append(" order by p.name ").toString();
		return  this.findPageByQuery(sql);
	}
	
	private StringBuffer genHqlStr(String result, String orgNameStr, TfQualBaseType type){
		StringBuffer sql=new StringBuffer("select ").append(result);
		sql.append(" from CmPeople p ,TfQualPilotTechrecord r ,BaseAirplanType a ,TfQualPilotLicence l ");
		sql.append(" where p.id=r.cmPeople.id and  a.id=l.atrid and l.atrid=r.baseAirplantype.id and l.plcseq=r.tfQualPilotLicence.plcseq and l.plcstus='0' and r.ptrcurrent='0' " );
		sql.append(" and r.ptgradeid in ('TA','TB','TC') ");
		String atrDesc=type.getTargetatrid().getAtrdesc();
		if(atrDesc!=null && !atrDesc.equals("")){
			sql.append(" and a.atrdesc='"+atrDesc+"'");
		}
		if(orgNameStr!=null && !orgNameStr.equals(""))
		{
			sql.append(" and p.sysOrganization.id in "+orgNameStr);
		}
		sql.append(" and p.id not in ( select st.cmPeople.id from TfQualDeclaraPilotStay st where st.tfQualBaseType.typeid='").append(type.getTypeid()).append("' and ( st.status='W' or st.status='N' or st.status is null) )");
		return sql;
	}
}
