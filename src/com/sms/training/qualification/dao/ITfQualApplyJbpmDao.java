package com.sms.training.qualification.dao;

import java.util.List;

import com.sinoframe.dao.IPublicDao;

public interface ITfQualApplyJbpmDao extends IPublicDao{

	int countPilotOfApply(String orgRole, String subGroupId);

	List<Object> findQualityTaskInfos(String orgRole, String subGroupId);
	
	String getForkTaskIdByIdAndOrgRole(String processInstanceId,String orgRole);
}
