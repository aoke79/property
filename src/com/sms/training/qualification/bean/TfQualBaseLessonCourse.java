package com.sms.training.qualification.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 课程信息 entity.
 * 
 * @author litongmeng
 * 
 */
@Entity
@Table(name = "TF_QUAL_BASE_LESSON_COURSE")
public class TfQualBaseLessonCourse implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7367786237187841072L;
	// id
	private String courseId;
	// 课次信息
	private TfQualBaseLesson tfQualBaseLesson;
	// 课程编号
	private int courseCode;
	// 科目
	private String subject;

	// Constructors

	/** default constructor */
	public TfQualBaseLessonCourse() {
	}

	public TfQualBaseLessonCourse(String courseId,int courseCode,String subject) {
		this.courseId=courseId;
		this.courseCode=courseCode;
		this.subject=subject;
	}

	/** full constructor */
	public TfQualBaseLessonCourse(TfQualBaseLesson tfQualBaseLesson,
			byte coursecode, String subject) {
		this.tfQualBaseLesson = tfQualBaseLesson;
		this.courseCode = coursecode;
		this.subject = subject;
	}

	// Property accessors

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LESSONID")
	public TfQualBaseLesson getTfQualBaseLesson() {
		return this.tfQualBaseLesson;
	}

	public void setTfQualBaseLesson(TfQualBaseLesson tfQualBaseLesson) {
		this.tfQualBaseLesson = tfQualBaseLesson;
	}

	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "COURSEID", unique = true, nullable = false, length = 36)
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Column(name = "COURSECODE", precision = 2, scale = 0)
	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	@Column(name = "SUBJECT", length = 500)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}