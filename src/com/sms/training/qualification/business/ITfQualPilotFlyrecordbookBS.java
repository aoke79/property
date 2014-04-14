package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualPilotFlyrecordbook;

public interface ITfQualPilotFlyrecordbookBS extends IService{
	/**
	 * 根据申报人员明细id 查找对应的 飞行经历记录list
	 * @param detailId 申报人员明细id
	 * @param sysPageInfo 分页信息
	 * @param orderByInfo 排序信息
	 * @param query 查询条件
	 * 
	 * @return 飞行经历记录list
	 */
	List<TfQualPilotFlyrecordbook> listRecordsByDetailId(String detailId, SysPageInfo sysPageInfo, SysOrderByInfo sysOrderByInfo, HashMap<String, String> query);
	
	/**
	 * 查找相关记录的最大条数
	 * @param detailId
	 * @return sysPageInfo.maxCount
	 */
	public long listSize(String detailId);
	/**
	 * 根据申报人员明细id和当前登录人  查找对应的 飞行经历记录
	 * @param detailId 申报人员明细id
	 * @param creator 当前登录人
	 * 
	 * @return 飞行经历记录
	 */
	public TfQualPilotFlyrecordbook findByDetailIdAndCreator(String detailId,String creator);
}
