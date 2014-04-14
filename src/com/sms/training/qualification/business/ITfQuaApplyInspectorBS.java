package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQuaApplyInspectorBS extends IService {

	public <T> List<T> getListByProperty(Class<T> cs,String property,Object value);

	List<TfQualBaseType> findTypeList(String subGroupId);
	
	//根据机型级机构 获取教员(TA TB TC)
	public List<Object> getAvailablePilotStayTmp(SysPageInfo sysPageInfo, TfQualBaseType tfQualBaseType, String orgNameStr, String pilotName);

	List<Object> getSelectedPilotStayTmp(SysPageInfo pageInfo, TfQualBaseType tfQualBaseType, String orgNameStr, String pilotName);
	

}
