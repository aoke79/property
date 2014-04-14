package com.sms.training.qualification.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualBaseLessonCourse;

public interface ITfQualBaseLessonCourseBS extends IService {
	
	/**
	 * 获取课程条数
	 * @param query 查询条件
	 * @return 课程条数
	 */
	long CourseListCount(HashMap<String, String> query);
	
	/**
	 * 获取课程列表
	 * @param sysPageInfo 分页信息
	 * @param sysOrderByInfo 排序信息
	 * @param query 查询条件
	 * @return list 课程列表
	 */
	List<TfQualBaseLessonCourse> searchCourseList(SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query);

	/**
	 * 判断该课程信息，是否被使用
	 * @param courseId
	 * @return
	 */
	public boolean hasBound(String courseId);
}
