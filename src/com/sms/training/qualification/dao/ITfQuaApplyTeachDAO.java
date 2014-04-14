package com.sms.training.qualification.dao;

import java.util.Date;
import java.util.List;

import com.sinoframe.dao.IPublicDao;

public interface ITfQuaApplyTeachDAO  extends IPublicDao{

	List<Object> findPilotList(String id, String orgIds, Date trainDate,String orgName,String subGroupId);

}
