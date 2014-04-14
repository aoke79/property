package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.BaseAirPlanType;
import com.sms.training.qualification.bean.TfQualPilotAppinforTeacherOne;

public interface ITfQualPilotAppinforTeacherOneBS extends IService{

	/**
	 * 查询总的条数
	 * @param query 条件集合
	 * @return
	 */
	public int getCountByDetailId(String detailId) ;
	/**
	 * 按条件分页查询，
	 * @param sysPageInfo 分页条件
	 * @param query 条件集合
	 * @param sysOrderByInfo 排序条件
	 * @return
	 */
	public List<TfQualPilotAppinforTeacherOne> findPageByDetailId(SysPageInfo sysPageInfo,
			 String detailId);
	/**
	 * 查找机型列表 
	 * @return
	 */
	public List<BaseAirPlanType> findAirplanList();
}
