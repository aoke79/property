package com.sms.training.qualification.business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;
import com.sms.training.qualification.business.ITfQualBasePilotInfoBS;

@Service("tfQualBasePilotInfoBS")
public class TfQualBasePilotInfoBS extends BaseBS implements ITfQualBasePilotInfoBS {

	@Override
	public TfQualBasePilotInfo getTfQualBasePilotInfo(String pilotid) {
		String hql = "from TfQualBasePilotInfo t where t.cmPeople.id='"+pilotid+"'";
		List<TfQualBasePilotInfo> TfQualBasePilotInfoList =  this.findPageByQuery(hql);
		TfQualBasePilotInfo tfQualBasePilotInfo = new TfQualBasePilotInfo();
		if(TfQualBasePilotInfoList != null && TfQualBasePilotInfoList.size()>0){
			tfQualBasePilotInfo = TfQualBasePilotInfoList.get(0);
		} 
		return tfQualBasePilotInfo;
	}

}
