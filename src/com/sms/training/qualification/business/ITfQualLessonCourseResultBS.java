package com.sms.training.qualification.business;

import java.util.Map;

import com.sinoframe.business.IService;
import com.sms.training.qualification.bean.TfQualLessonComment;

public interface ITfQualLessonCourseResultBS extends IService {
/**
 * 保存课程成绩
 * @param idsAndStatus 信息id与对应状态的map
 * @param lessoncommentid 课次评语id
 */
	void save(Map<String, String> idsAndStatus, TfQualLessonComment comment);

}
