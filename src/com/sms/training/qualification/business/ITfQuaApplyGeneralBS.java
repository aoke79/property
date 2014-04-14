package com.sms.training.qualification.business;

import java.util.*;

import com.sinoframe.business.IService;
 
public interface ITfQuaApplyGeneralBS extends IService{
	
	public void doComputingGeneralStaff(String typeids,String orgid,String trainDate,String orgName,String subGroupId);
	public List<Object> getPilotListByOrgid(String atrid,String orgid,String trainDate,String orgName,String subGroupId);
}
