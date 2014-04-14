package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseConditions;

public interface ITfQualBaseConditionsBS extends IService {
	/**
	 * 
	 * @param sysPageInfo  分页信息
	 * @param query 查询条件
	 * @param sysOrderByInfo 排序信息
	 * @return  
	 */
	public List<TfQualBaseConditions> findAllByQuery(SysPageInfo sysPageInfo,HashMap<String, String> query ,SysOrderByInfo sysOrderByInfo);
	public void BatchDeleteByIds(TfQualBaseConditions tfQualBaseConditions,String ids);
	public void saveAndUpdate(TfQualBaseConditions tfQualBaseConditions);
	

}
