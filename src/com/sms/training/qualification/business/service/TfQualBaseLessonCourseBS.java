package com.sms.training.qualification.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.service.BaseBS;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;
import com.sms.training.qualification.business.ITfQualBaseLessonCourseBS;

@Service("tfQualBaseLessonCourseBS")
public class TfQualBaseLessonCourseBS extends BaseBS implements
		ITfQualBaseLessonCourseBS {
	/**
	 * 获取课程条数
	 * @param query 查询条件
	 * @return 课程条数
	 */
	@Override
	public long CourseListCount(HashMap<String, String> query) {
		long colCount = 0;
		
		String hql = "select count(*) from TfQualBaseLessonCourse where 1=1 ";

		colCount = this.getCountByHQL(hql, query);

		return colCount;
	}
	
	/**
	 * 获取课程列表
	 * @param sysPageInfo 分页信息
	 * @param sysOrderByInfo 排序信息
	 * @param query 查询条件
	 * @return list 课程列表
	 */
	@Override
	public List<TfQualBaseLessonCourse> searchCourseList(
			SysPageInfo sysPageInfo, SysOrderByInfo sysOrderByInfo,
			HashMap<String, String> query) {
		List<TfQualBaseLessonCourse> list = new ArrayList<TfQualBaseLessonCourse>();
		
		String hql = "from TfQualBaseLessonCourse where 1=1 ";
		list = this.findPageByQuery(sysPageInfo, hql, query, " courseCode asc,subject desc ");
		return list;
	}
	
	@Override
	public boolean hasBound(String courseId){
		String hql = "select count(*) from TfQualLessonCourseResult rst where courseid = '"+courseId+"'";
		int countResult = this.getCountByHql(hql);
		return countResult > 0;
	}

}
