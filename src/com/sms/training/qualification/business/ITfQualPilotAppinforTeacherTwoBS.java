package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherTwo;

public interface ITfQualPilotAppinforTeacherTwoBS extends IService{
	/**
	 * 按条件分页查询，
	 * @param detailId 申报人员明细ID条件
	 * @return
	 */
	public List<TfQualPilotAppinforTeacherTwo> findPageByDetailId(String detailId);
	/**
	 * 查找机型列表 
	 * @return
	 */
	public List<BaseAirPlanType> findAirplanList();
}
