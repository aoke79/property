package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseLesson;
import com.sms.training.qualification.bean.TfQualBaseType;

public interface ITfQualBaseLessonBS extends IService {

	/**
	 * 
	 * @param query
	 * @return
	 */
	public long lessonListCount(HashMap<String, String> query);
	
	/**
	 * 
	 * @param sysPageInfo
	 * @param sysOrderByInfo
	 * @param query
	 * @return
	 */
	public List<TfQualBaseLesson> searchLessonList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query);
	/**
	 * 获取资质类型条数
	 * @param query
	 * @return 资质类型条数
	 */
	long TypeListCount(HashMap<String, String> decodeQuery);
	/**
	 * 获取资质类型列表
	 * @param sysPageInfo 
	 * @param sysOrderByInfo 
	 * @param query 查询条件
	 * 
	 * @return 资质类型列表
	 */
	List<TfQualBaseType> searchTypeList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> decodeQuery);
}
