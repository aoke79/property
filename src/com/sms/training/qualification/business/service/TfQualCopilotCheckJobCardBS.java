package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualCopilotChecklist;
import com.sms.training.qualification.bean.TfQualDeclaraPilot;
import com.sms.training.qualification.business.ITfQualCopilotCheckJobCardBS;

@Service("tfQualCopilotCheckJobCardBS")
public class TfQualCopilotCheckJobCardBS extends BaseBS implements ITfQualCopilotCheckJobCardBS {

	public TfQualCopilotChecklist getPageByDetailsid(String detailsid) {
		
		String hql = " from TfQualCopilotChecklist tp where tp.tfQualDeclaraPilot.detailsid='"+detailsid+"' ";
		List<TfQualCopilotChecklist> list = this.findPageByQuery(hql);
		if (list != null && list.size() != 0) {
			return list.get(0);
		}
		
		return null;
	}

	public String getPlcno(String pilotId) {
		String plcno = null;
		
		StringBuffer hql=new StringBuffer();
		hql.append("select lc.plcno from TfQualPilotLicence lc where lc.pilotid = '")
			.append(pilotId).append("'" )
			.append(" and lc.plcstus= '0'");
		
		List<String> plcnos = this.findPageByQuery(hql.toString());
		if(plcnos != null && plcnos.size() != 0){
			plcno = plcnos.get(0);
		}
		
		return plcno;
	}
	
	public String getQualification(String pilotId) {
		String qualification = null;
	
		StringBuffer hql=new StringBuffer();
		hql.append("select en.qualification from EnExamQualification en where en.examineeId ='")
			.append(pilotId).append("'" );
		
		List<Integer> qualifications = this.findPageByQuery(hql.toString());
		if(qualifications != null && qualifications.size() != 0){
			qualification = qualifications.get(0).toString();
		}
		return qualification;
	}
}
