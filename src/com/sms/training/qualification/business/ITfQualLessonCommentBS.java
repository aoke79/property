package com.sms.training.qualification.business;

import java.util.List;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.MyResult;
import com.sms.training.qualification.bean.TfQualLessonComment;
import com.sms.training.qualification.bean.TfQualLessonCourseResult;

public interface ITfQualLessonCommentBS extends IService {
	
	/**
	 * 根据根据 申报人员明细id 和 课次id 查找到对应的课次评语
	 * @param detailsid 申报人员明细id
	 * @param lessonId 课次id
	 * @return 课次评语
	 */
	TfQualLessonComment findComment(String detailsid, String lessonId);
	
	/**
	 * 根据 课次id 查找到对应的 课程成绩(MyResult)list
	 * @param commentId 课次评语id
	 * @param lessonId 课次id
	 * @see MyResult
	 */
	List<MyResult> findMyResultList(String lessonId, String commentId);
	
	/**
	 * 根据课次评语查找到对应的 课程成绩list
	 * @param comment 课次评语
	 */
	List<TfQualLessonCourseResult> findResultList(String commentId);
	
	/**
	 * 判断当前登陆者是否已全部填完课程单子
	 * @param detailsid 申报人员明细id
	 * @param typeId 资质类型id
	 * @param isT 当前登陆者是教员还是检查员
	 * @return 
	 */
	boolean checkAllCommentIfSave(String detailsid,String isT, String typeId);

}
