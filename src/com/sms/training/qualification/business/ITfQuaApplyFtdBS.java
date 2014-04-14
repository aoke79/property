package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.CmUser;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQuaApplyFtdBS extends IService {
	/**
	 * 根据机型及机构 获取教员(TA TB TC)
	 * @param tfQualBaseType
	 * @param orgNameStr
	 * @return
	 */
	public List<Object> getAvailablePilotStayList(TfQualBaseType tfQualBaseType, String orgNameStr);
	/**
	 * 创建申报信息
	 * @param typeid资质类型
	 * @param ids人员ID
	 */
	public String createDeclarInfo(String typeid,String ids,CmUser cmUser);
	
	/**
	 * 将待申报人员 保存到stay表中
	 * @param typeid
	 * @param ids
	 * @param cmUser
	 */
	public void savePilotStays(String typeid, String ids, CmUser cmUser);

}
