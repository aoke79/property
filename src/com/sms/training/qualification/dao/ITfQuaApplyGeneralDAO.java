package com.sms.training.qualification.dao;

import java.util.List;

import com.sinoframe.dao.IPublicDao;

public interface ITfQuaApplyGeneralDAO extends IPublicDao{
	public List<Object> getPilotListByOrgid(String atrid,String orgid,String date,String orgName,String subGroupId);
}
