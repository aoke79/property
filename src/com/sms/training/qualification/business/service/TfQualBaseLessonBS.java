package com.sms.training.qualification.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sinoframe.common.util.Util;
import com.sms.training.qualification.bean.TfQualBaseLesson;
import com.sms.training.qualification.bean.TfQualBaseType;
import com.sms.training.qualification.business.ITfQualBaseLessonBS;
import com.sms.training.qualification.dao.ITfQualBaseLessonDao;
/**
 * 课次信息 Service
 * @author licumn
 *
 */
@Service("tfQualBaseLessonBS")
public class TfQualBaseLessonBS extends BaseBS implements ITfQualBaseLessonBS {
	
	private ITfQualBaseLessonDao tfQualBaseLessonDao;

	public ITfQualBaseLessonDao getTfQualBaseLessonDao() {
		return tfQualBaseLessonDao;
	}

	@Resource
	public void setTfQualBaseLessonDao(ITfQualBaseLessonDao tfQualBaseLessonDao) {
		this.tfQualBaseLessonDao = tfQualBaseLessonDao;
	}
	/**
	 * 
	 * @param query
	 * @return
	 */
	public long lessonListCount(HashMap<String, String> query) {
		long colCount = 0;
		
		String hql = "select count(*) from TfQualBaseLesson where 1=1 ";

		//显示时需要的字段
		String[] queryShown = {"like_typeName"};
		
		//查询时需要的字段
		String[] queryCondition = {"like_tfQualBaseType.typedesc"};
		
		this.getQueryCondition(queryShown, queryCondition, Util.decodeQuery(query));
		
		colCount = this.getCountByHQL(hql, query);

		//显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		
		return colCount;
	}
	
	/**
	 * 
	 * @param sysPageInfo
	 * @param sysOrderByInfo
	 * @param query
	 * @return
	 */
	public List<TfQualBaseLesson> searchLessonList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query) {
		List<TfQualBaseLesson> lessonList = new ArrayList<TfQualBaseLesson>();
		
		String hql = "from TfQualBaseLesson where 1=1 ";
		
		//显示时需要的字段
		String[] queryShown = {"like_typeName"};
		
		//查询时需要的字段
		String[] queryCondition = {"like_tfQualBaseType.typedesc"};
		
		this.getQueryCondition(queryShown, queryCondition, Util.decodeQuery(query));
		
		lessonList = this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		
		return lessonList;
	}
	/**
	 * 获取资质类型条数
	 * @param query
	 * @return 资质类型条数
	 */
	@Override
	public long TypeListCount(HashMap<String, String> query) {
		long colCount = 0;
		
		String hql = "select count(*) from TfQualBaseType where 1=1 ";

		//显示时需要的字段
		String[] queryShown = {"like_typeGroup"};
		
		//查询时需要的字段
		String[] queryCondition = {"like_tfQualBaseTypeGroup.qtgroupdesc"};
		
		this.getQueryCondition(queryShown, queryCondition, Util.decodeQuery(query));
		
		colCount = this.getCountByHQL(hql, query);

		//显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		
		return colCount;
	}
	/**
	 * 获取资质类型列表
	 * @param sysPageInfo 
	 * @param sysOrderByInfo 
	 * @param query 查询条件
	 * 
	 * @return 资质类型列表
	 */
	@Override
	public List<TfQualBaseType> searchTypeList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query) {
		List<TfQualBaseType> typeList = new ArrayList<TfQualBaseType>();
		
		String hql = "from TfQualBaseType where 1=1 ";
		
		//显示时需要的字段
		String[] queryShown = {"like_typeGroup"};
		
		//查询时需要的字段
		String[] queryCondition = {"like_tfQualBaseTypeGroup.qtgroupdesc"};
		
		this.getQueryCondition(queryShown, queryCondition, Util.decodeQuery(query));
		
		typeList = this.findPageByQuery(sysPageInfo, hql, query, sysOrderByInfo);
		
		//显示特殊查询 条件的数据
		this.getQueryShow(queryShown, queryCondition, Util.decodeQuery(query));
		
		return typeList;
	}

}
