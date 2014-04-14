package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQuaApplyFXJYtheoryBS extends IService {
	
	public List<Object> getAvailablePilotStayList(TfQualBaseType type, String orgNameStr);
}
