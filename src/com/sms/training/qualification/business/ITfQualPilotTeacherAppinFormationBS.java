package com.sms.training.qualification.business;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotTeacherAppinFormation;

public interface ITfQualPilotTeacherAppinFormationBS extends IService{
	/**
	 * 根据申报人员id查找是否有记录
	 * @param detailsid
	 * @return
	 */
	public TfQualPilotTeacherAppinFormation findByDetailsid(String detailsid);
}
