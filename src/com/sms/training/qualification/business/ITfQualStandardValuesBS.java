package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseAccessconditions;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.bean.TfQualBaseTypeGroup;

public interface ITfQualStandardValuesBS extends IService {
 /**
  * 得到资质类型列表
  * @return
  */
	public List<TfQualBaseType> getTfQualBaseTypeList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo);
	/**
	 * 查询训练类型分组列表
	 * @return
	 */
	public <T extends Object> List<T> getSelectList(String entityClass);
	/**
	 * 保存tfQualBaseAccessconditions 对象
	 * @param tfQualBaseAccessconditions
	 */
	public void saveTfQualBaseAccessconditions(TfQualBaseAccessconditions tfQualBaseAccessconditions);
	
	public List<TfQualBaseAccessconditions> getTfQualBaseAccessconditionsList(SysPageInfo sysPageInfo,HashMap<String, String> query,SysOrderByInfo sysOrderByInfo);
}	
