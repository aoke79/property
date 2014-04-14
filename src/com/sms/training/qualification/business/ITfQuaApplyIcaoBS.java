package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.SysOrganization;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.EnExamQualification;

public interface ITfQuaApplyIcaoBS extends IService {
	
	public List<EnExamQualification> findFuKaoList(String type,String orgNameString);
	
	public void recursionOrganization(SysOrganization currentOrganizaion,List<SysOrganization> list);
	
	public List<SysOrganization> getOrgList(String orgId);
	
	public String getOrgName(SysOrganization org);
	
}
