package com.sms.training.qualification.business;

import java.util.Date;

import com.sinoframe.business.IService;

public interface ITfQuaApplyTeachBS extends IService {

	void doComputingTeachStaff(String typeId, String orgIdStr, Date trainDate,String orgName,String subGroupId);
	
}
