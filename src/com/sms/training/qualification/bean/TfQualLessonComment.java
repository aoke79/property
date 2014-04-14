package com.sms.training.qualification.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 课次评语 entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_LESSON_COMMENT")
public class TfQualLessonComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6863383716118471220L;
	// id
	private String lessoncommentid;
	// 申报人员明细
	private String detailsid;
	// 课次id
	private String lessonid;
	// 评语
	private String commentinfo;
	// 教员、检查员userId
	private String teacherid;
	// 类型（教员、检查员）
	private String teachertype;
	// 飞行员hrid
	private String pilotid;
	// 签字日期
	private Date signingdate;
	// 课程成绩
	private Set<TfQualLessonCourseResult> tfQualLessonCourseResults = new HashSet<TfQualLessonCourseResult>(
			0);

	// Constructors

	/** default constructor */
	public TfQualLessonComment() {
	}

	/** full constructor */
	public TfQualLessonComment(String detailsid, String lessonid,
			String commentinfo, String teacherid, String teachertype,
			String pilotid,
			Set<TfQualLessonCourseResult> tfQualLessonCourseResults) {
		this.detailsid = detailsid;
		this.lessonid = lessonid;
		this.commentinfo = commentinfo;
		this.teacherid = teacherid;
		this.teachertype = teachertype;
		this.pilotid = pilotid;
		this.tfQualLessonCourseResults = tfQualLessonCourseResults;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LESSONCOMMENTID", unique = true, nullable = false, length = 36)
	public String getLessoncommentid() {
		return this.lessoncommentid;
	}

	public void setLessoncommentid(String lessoncommentid) {
		this.lessoncommentid = lessoncommentid;
	}

	@Column(name = "DETAILSID", length = 36)
	public String getDetailsid() {
		return this.detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}

	@Column(name = "LESSONID", length = 36)
	public String getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(String lessonid) {
		this.lessonid = lessonid;
	}

	@Column(name = "COMMENTINFO", length = 500)
	public String getCommentinfo() {
		return this.commentinfo;
	}

	public void setCommentinfo(String commentinfo) {
		this.commentinfo = commentinfo;
	}

	@Column(name = "TEACHERID", length = 36)
	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

	@Column(name = "TEACHERTYPE", length = 2)
	public String getTeachertype() {
		return this.teachertype;
	}

	public void setTeachertype(String teachertype) {
		this.teachertype = teachertype;
	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SIGNINGDATE", length = 7)
	public Date getSigningdate() {
		return this.signingdate;
	}

	public void setSigningdate(Date signingdate) {
		this.signingdate = signingdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tfQualLessonComment")
	public Set<TfQualLessonCourseResult> getTfQualLessonCourseResults() {
		return this.tfQualLessonCourseResults;
	}

	public void setTfQualLessonCourseResults(
			Set<TfQualLessonCourseResult> tfQualLessonCourseResults) {
		this.tfQualLessonCourseResults = tfQualLessonCourseResults;
	}

}