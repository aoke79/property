package com.sms.training.qualification.business.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;
import com.sms.training.qualification.business.ITfQualDeclaraPilotStayBS;
@Service("tfQualDeclaraPilotStayBS")
public class TfQualDeclaraPilotStayBS extends BaseBS implements	ITfQualDeclaraPilotStayBS {

	@Override
	public List<TfQualDeclaraPilotStay> findTfQualDeclaraPilotStayByPeopleId(String peopleid) {
		String hql="from TfQualDeclaraPilotStay le where le.cmPeople.id='"+peopleid+"'";
		return this.findPageByQuery(hql);
	}

	@Override
	public void updatePilotStayByPilotId(String pilotId,String qtGroupId) {
		String hql="";
		if("QUAL_CT_GSFXJCY".equals(qtGroupId)|| "QUAL_CT_JFWRDB".equals(qtGroupId) 
				||"QUAL_CT_SLJCY".equals(qtGroupId) || "QUAL_TR_FJSZJX".equals(qtGroupId)
				|| "QUAL_TR_JZZJX".equals(qtGroupId)){
			hql = "delete from TfQualDeclaraPilotStay t where t.cmPeople.id = '"+pilotId+"'";
		}else{
			hql = "update TfQualDeclaraPilotStay t set t.status=null where t.cmPeople.id = '"+pilotId+"'";
		}
		this.executeUpdate(hql);
	}

}
