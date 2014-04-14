package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualCopilotChecklist;

public interface ITfQualCopilotCheckJobCardBS extends IService{

	public TfQualCopilotChecklist getPageByDetailsid(String detailsid);

	public String getPlcno(String pilotId);
	
	public String getQualification(String pilotId);
}
