package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualDeclaraPilotStay;

public interface ITfQualDeclaraPilotStayBS extends IService {
	/**
	 * 查询待上报人员
	 * @param sysPageInfo
	 * @return
	 */
	public List<TfQualDeclaraPilotStay> findTfQualDeclaraPilotStayByPeopleId(String peopleid);
	
	/**
	 * 根据 pilotid删除或更改 tf_qual_declare_pilot_stay表中的数据 
	 * 当是转机型或者检查员流程时，删除，其他流程更改
	 * @param pilotId
	 */
	public void updatePilotStayByPilotId(String pilotId,String qtGroupId);
}
