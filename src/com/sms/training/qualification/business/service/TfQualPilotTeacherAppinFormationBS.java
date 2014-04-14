package com.sms.training.qualification.business.service;

import org.springframework.stereotype.Service;

import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualPilotTeacherAppinFormation;
import com.sms.training.qualification.business.ITfQualPilotTeacherAppinFormationBS;
@Service("tfQualPilotTeacherAppinFormationBS")
public class TfQualPilotTeacherAppinFormationBS extends BaseBS implements ITfQualPilotTeacherAppinFormationBS{

	@Override
	public TfQualPilotTeacherAppinFormation findByDetailsid(String detailsid) {

		return this.findUniqueBySingleQuery("TfQualPilotTeacherAppinFormation", "tfQualDeclaraPilot.detailsid", detailsid);
	}

}
