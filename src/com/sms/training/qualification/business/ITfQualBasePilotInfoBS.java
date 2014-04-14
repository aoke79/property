package com.sms.training.qualification.business;

import java.io.Serializable;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBasePilotInfo;

public interface ITfQualBasePilotInfoBS extends IService {
	/**
	 * 得到飞行员准入信息
	 * @param pilotid
	 * @return
	 */
	public TfQualBasePilotInfo getTfQualBasePilotInfo(String pilotid);
}
