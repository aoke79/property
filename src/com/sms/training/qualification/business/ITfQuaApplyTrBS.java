package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQuaApplyTrBS extends IService{

	/**
	 * 根据查询条件查询，获取符合条件的可申报人员信息
	 * @param pageInfo 分页信息
	 * @param tfQualBaseType 资质类型
	 * @param orgNameStr 机构id串
	 * @param pilotName 查询参数，姓名
	 * @param subGroupId 小类id
	 * @return
	 */
	public List<Object> getAvailablePilotStayTmp(SysPageInfo pageInfo,TfQualBaseType tfQualBaseType, String orgNameStr, String pilotName, String subGroupId);
	
	/**
	 * 根据查询条件查询，获取符合条件的 已经选择的 待申报人员信息
	 * @param sysPageInfo 分页信息
	 * @param typeId 资质类型id
	 * @param orgNameStr 机构id串
	 * @return
	 */
	public List<Object> getSelectedPilotStayTmp(SysPageInfo sysPageInfo, String typeId, String orgNameStr, String pilotName);
}
