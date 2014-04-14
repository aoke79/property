package com.sms.training.qualification.bean;

/**
 * 自定义 课程成绩 类，为了方便页面显示
 * @author Administrator
 * 
 */
public class MyResult {
	//课程信息
	TfQualBaseLessonCourse courseInfo;
	//课程成绩 （状态：通过与否   Y/N）
	String status;

	public MyResult(TfQualBaseLessonCourse cs, String resultsstatus) {
		this.courseInfo = cs;
		this.status = resultsstatus;
	}

	public TfQualBaseLessonCourse getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(TfQualBaseLessonCourse courseInfo) {
		this.courseInfo = courseInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}