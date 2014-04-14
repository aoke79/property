package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TfQualTeacherSchedule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_TEACHER_SCHEDULE")
public class TfQualTeacherSchedule implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	//ID
	private String scheduleid;
	//飞行员ID
	private String pilotid;
	//法规完成时间
	private Date groundDate;
	//法规教员
	private String groundTeacher;
	//公司运行手册和运行规划时间
	private Date groundDate2;
	//公司运行手册和运行规划教员
	private String groundTeacher2;
	//教员基本原理和授课计划时间
	private Date groundDate3;
	//教员基本原理和授课计划教员
	private String groundTeacher3;
	//人为因素和机组资源管理时间
	private Date groundDate4;
	//人为因素和机组资源管理教员
	private String groundTeacher4;
	//教学记录表格飞行记录本的填写时间
	private Date groundDate5;
	//教学记录表格飞行记录本的填写教员
	private String groundTeacher5;
	//飞行事件分析时间
	private Date groundDate6;
	//飞行事件分析教员
	private String groundTeacher6;
	//A右座操作飞机的特点时间
	private Date scheduleDateA1;
	//A右座操作飞机的特点教员
	private String scheduleTeacherA1;
	//A非正常程序的训练时间
	private Date scheduleDateA2;
	//A非正常程序的训练教员
	private String scheduleTeacherA2;
	//A飞行教学和飞行检查练习时间
	private Date scheduleDateA3;
	//A飞行教学和飞行检查练习教员
	private String scheduleTeacherA3;
	//A偏差动作的防止和纠正时间
	private Date scheduleDateA4;
	//A偏差动作的防止和纠正教员
	private String scheduleTeacherA4;
	//A讲评的方法和技巧时间
	private Date scheduleDateA5;
	//A讲评的方法和技巧教员
	private String scheduleTeacherA5;
	//B模拟机教员操纵面板的使用时间
	private Date scheduleDateB1;
	//B模拟机教员操纵面板的使用教员
	private String scheduleTeacherB1;
	//B飞行课程的下达和讲解时间
	private Date scheduleDateB2;
	//B飞行课程的下达和讲解教员
	private String scheduleTeacherB2;
	//B飞行科目的设置时间
	private Date scheduleDateB4;
	//B飞行科目的设置教员
	private String scheduleTeacherB4;
	//B飞行教学和飞行检查练习时间
	private Date scheduleDateB5;
	//B飞行教学和飞行检查练习教员
	private String scheduleTeacherB5;
	//B偏差动作的防止和纠正时间
	private Date scheduleDateB6;
	//B偏差动作的防止和纠正教员
	private String scheduleTeacherB6;
	//B讲评的方法和技巧时间
	private Date scheduleDateB7;
	//B讲评的方法和技巧教员
	private String scheduleTeacherB7;
	//C飞机外部检查时间
	private Date scheduleDateC1;
	//C飞机外部检查教员
	private String scheduleTeacherC1;
	//C飞机设备的使用时间
	private Date scheduleDateC2;
	//C飞机设备的使用教员
	private String scheduleTeacherC2;
	//C飞机课程的下达讲解时间
	private Date scheduleDateC3;
	//C飞机课程的下达讲解教员
	private String scheduleTeacherC3;
	//C正常非正常飞行程序时间
	private Date scheduleDateC4;
	//C正常非正常飞行程序教员
	private String scheduleTeacherC4;
	//C飞机的操纵时间
	private Date scheduleDateC5;
	//C飞机的操纵教员
	private String scheduleTeacherC5;
	//C偏差动作的防止和纠正时间
	private Date scheduleDateC6;
	//C偏差动作的防止和纠正教员
	private String scheduleTeacherC6;
	//C讲评的方法和技巧时间
	private Date scheduleDateC7;
	//C讲评的方法和技巧教员
	private String scheduleTeacherC7;
	//A类教员批准
	private String approveA;
	//A类教员批准签字
	private String approveSignatureA;
	//A类教员批准日期
	private Date approveDateA;
	//B类教员批准
	private String approveB;
	//B类教员批准签字
	private String approveSignatureB;
	//B类教员批准日期
	private Date approveDateB;
	//C类教员批准
	private String approveC;
	//C类教员批准签字
	private String approveSignatureC;
	//C类教员批准日期
	private Date approveDateC;

	// Constructors

	/** default constructor */
	public TfQualTeacherSchedule() {
	}

	/** minimal constructor */
	public TfQualTeacherSchedule(String scheduleid) {
		this.scheduleid = scheduleid;
	}

	/** full constructor */
	public TfQualTeacherSchedule(String scheduleid, String pilotid,
			Date groundDate, String groundTeacher, Date groundDate2,
			String groundTeacher2, Date groundDate3, String groundTeacher3,
			Date groundDate4, String groundTeacher4, Date groundDate5,
			String groundTeacher5, Date groundDate6, String groundTeacher6,
			Date scheduleDateA1, String scheduleTeacherA1, Date scheduleDateA2,
			String scheduleTeacherA2, Date scheduleDateA3,
			String scheduleTeacherA3, Date scheduleDateA4,
			String scheduleTeacherA4, Date scheduleDateA5,
			String scheduleTeacherA5, Date scheduleDateB1,
			String scheduleTeacherB1, Date scheduleDateB2,
			String scheduleTeacherB2, Date scheduleDateB4,
			String scheduleTeacherB4, Date scheduleDateB5,
			String scheduleTeacherB5, Date scheduleDateB6,
			String scheduleTeacherB6, Date scheduleDateB7,
			String scheduleTeacherB7, Date scheduleDateC1,
			String scheduleTeacherC1, Date scheduleDateC2,
			String scheduleTeacherC2, Date scheduleDateC3,
			String scheduleTeacherC3, Date scheduleDateC4,
			String scheduleTeacherC4, Date scheduleDateC5,
			String scheduleTeacherC5, Date scheduleDateC6,
			String scheduleTeacherC6, Date scheduleDateC7,
			String scheduleTeacherC7, String approveA,
			String approveSignatureA, Date approveDateA, String approveB,
			String approveSignatureB, Date approveDateB, String approveC,
			String approveSignatureC, Date approveDateC) {
		this.scheduleid = scheduleid;
		this.pilotid = pilotid;
		this.groundDate = groundDate;
		this.groundTeacher = groundTeacher;
		this.groundDate2 = groundDate2;
		this.groundTeacher2 = groundTeacher2;
		this.groundDate3 = groundDate3;
		this.groundTeacher3 = groundTeacher3;
		this.groundDate4 = groundDate4;
		this.groundTeacher4 = groundTeacher4;
		this.groundDate5 = groundDate5;
		this.groundTeacher5 = groundTeacher5;
		this.groundDate6 = groundDate6;
		this.groundTeacher6 = groundTeacher6;
		this.scheduleDateA1 = scheduleDateA1;
		this.scheduleTeacherA1 = scheduleTeacherA1;
		this.scheduleDateA2 = scheduleDateA2;
		this.scheduleTeacherA2 = scheduleTeacherA2;
		this.scheduleDateA3 = scheduleDateA3;
		this.scheduleTeacherA3 = scheduleTeacherA3;
		this.scheduleDateA4 = scheduleDateA4;
		this.scheduleTeacherA4 = scheduleTeacherA4;
		this.scheduleDateA5 = scheduleDateA5;
		this.scheduleTeacherA5 = scheduleTeacherA5;
		this.scheduleDateB1 = scheduleDateB1;
		this.scheduleTeacherB1 = scheduleTeacherB1;
		this.scheduleDateB2 = scheduleDateB2;
		this.scheduleTeacherB2 = scheduleTeacherB2;
		this.scheduleDateB4 = scheduleDateB4;
		this.scheduleTeacherB4 = scheduleTeacherB4;
		this.scheduleDateB5 = scheduleDateB5;
		this.scheduleTeacherB5 = scheduleTeacherB5;
		this.scheduleDateB6 = scheduleDateB6;
		this.scheduleTeacherB6 = scheduleTeacherB6;
		this.scheduleDateB7 = scheduleDateB7;
		this.scheduleTeacherB7 = scheduleTeacherB7;
		this.scheduleDateC1 = scheduleDateC1;
		this.scheduleTeacherC1 = scheduleTeacherC1;
		this.scheduleDateC2 = scheduleDateC2;
		this.scheduleTeacherC2 = scheduleTeacherC2;
		this.scheduleDateC3 = scheduleDateC3;
		this.scheduleTeacherC3 = scheduleTeacherC3;
		this.scheduleDateC4 = scheduleDateC4;
		this.scheduleTeacherC4 = scheduleTeacherC4;
		this.scheduleDateC5 = scheduleDateC5;
		this.scheduleTeacherC5 = scheduleTeacherC5;
		this.scheduleDateC6 = scheduleDateC6;
		this.scheduleTeacherC6 = scheduleTeacherC6;
		this.scheduleDateC7 = scheduleDateC7;
		this.scheduleTeacherC7 = scheduleTeacherC7;
		this.approveA = approveA;
		this.approveSignatureA = approveSignatureA;
		this.approveDateA = approveDateA;
		this.approveB = approveB;
		this.approveSignatureB = approveSignatureB;
		this.approveDateB = approveDateB;
		this.approveC = approveC;
		this.approveSignatureC = approveSignatureC;
		this.approveDateC = approveDateC;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "SCHEDULEID", unique = true, nullable = false, length = 36)
	public String getScheduleid() {
		return this.scheduleid;
	}

	public void setScheduleid(String scheduleid) {
		this.scheduleid = scheduleid;
	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE", length = 7)
	public Date getGroundDate() {
		return this.groundDate;
	}

	public void setGroundDate(Date groundDate) {
		this.groundDate = groundDate;
	}

	@Column(name = "GROUND_TEACHER", length = 36)
	public String getGroundTeacher() {
		return this.groundTeacher;
	}

	public void setGroundTeacher(String groundTeacher) {
		this.groundTeacher = groundTeacher;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE2", length = 7)
	public Date getGroundDate2() {
		return this.groundDate2;
	}

	public void setGroundDate2(Date groundDate2) {
		this.groundDate2 = groundDate2;
	}

	@Column(name = "GROUND_TEACHER2", length = 36)
	public String getGroundTeacher2() {
		return this.groundTeacher2;
	}

	public void setGroundTeacher2(String groundTeacher2) {
		this.groundTeacher2 = groundTeacher2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE3", length = 7)
	public Date getGroundDate3() {
		return this.groundDate3;
	}

	public void setGroundDate3(Date groundDate3) {
		this.groundDate3 = groundDate3;
	}

	@Column(name = "GROUND_TEACHER3", length = 36)
	public String getGroundTeacher3() {
		return this.groundTeacher3;
	}

	public void setGroundTeacher3(String groundTeacher3) {
		this.groundTeacher3 = groundTeacher3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE4", length = 7)
	public Date getGroundDate4() {
		return this.groundDate4;
	}

	public void setGroundDate4(Date groundDate4) {
		this.groundDate4 = groundDate4;
	}

	@Column(name = "GROUND_TEACHER4", length = 36)
	public String getGroundTeacher4() {
		return this.groundTeacher4;
	}

	public void setGroundTeacher4(String groundTeacher4) {
		this.groundTeacher4 = groundTeacher4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE5", length = 7)
	public Date getGroundDate5() {
		return this.groundDate5;
	}

	public void setGroundDate5(Date groundDate5) {
		this.groundDate5 = groundDate5;
	}

	@Column(name = "GROUND_TEACHER5", length = 36)
	public String getGroundTeacher5() {
		return this.groundTeacher5;
	}

	public void setGroundTeacher5(String groundTeacher5) {
		this.groundTeacher5 = groundTeacher5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GROUND_DATE6", length = 7)
	public Date getGroundDate6() {
		return this.groundDate6;
	}

	public void setGroundDate6(Date groundDate6) {
		this.groundDate6 = groundDate6;
	}

	@Column(name = "GROUND_TEACHER6", length = 36)
	public String getGroundTeacher6() {
		return this.groundTeacher6;
	}

	public void setGroundTeacher6(String groundTeacher6) {
		this.groundTeacher6 = groundTeacher6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_A1", length = 7)
	public Date getScheduleDateA1() {
		return this.scheduleDateA1;
	}

	public void setScheduleDateA1(Date scheduleDateA1) {
		this.scheduleDateA1 = scheduleDateA1;
	}

	@Column(name = "SCHEDULE_TEACHER_A1", length = 36)
	public String getScheduleTeacherA1() {
		return this.scheduleTeacherA1;
	}

	public void setScheduleTeacherA1(String scheduleTeacherA1) {
		this.scheduleTeacherA1 = scheduleTeacherA1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_A2", length = 7)
	public Date getScheduleDateA2() {
		return this.scheduleDateA2;
	}

	public void setScheduleDateA2(Date scheduleDateA2) {
		this.scheduleDateA2 = scheduleDateA2;
	}

	@Column(name = "SCHEDULE_TEACHER_A2", length = 36)
	public String getScheduleTeacherA2() {
		return this.scheduleTeacherA2;
	}

	public void setScheduleTeacherA2(String scheduleTeacherA2) {
		this.scheduleTeacherA2 = scheduleTeacherA2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_A3", length = 7)
	public Date getScheduleDateA3() {
		return this.scheduleDateA3;
	}

	public void setScheduleDateA3(Date scheduleDateA3) {
		this.scheduleDateA3 = scheduleDateA3;
	}

	@Column(name = "SCHEDULE_TEACHER_A3", length = 36)
	public String getScheduleTeacherA3() {
		return this.scheduleTeacherA3;
	}

	public void setScheduleTeacherA3(String scheduleTeacherA3) {
		this.scheduleTeacherA3 = scheduleTeacherA3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_A4", length = 7)
	public Date getScheduleDateA4() {
		return this.scheduleDateA4;
	}

	public void setScheduleDateA4(Date scheduleDateA4) {
		this.scheduleDateA4 = scheduleDateA4;
	}

	@Column(name = "SCHEDULE_TEACHER_A4", length = 36)
	public String getScheduleTeacherA4() {
		return this.scheduleTeacherA4;
	}

	public void setScheduleTeacherA4(String scheduleTeacherA4) {
		this.scheduleTeacherA4 = scheduleTeacherA4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_A5", length = 7)
	public Date getScheduleDateA5() {
		return this.scheduleDateA5;
	}

	public void setScheduleDateA5(Date scheduleDateA5) {
		this.scheduleDateA5 = scheduleDateA5;
	}

	@Column(name = "SCHEDULE_TEACHER_A5", length = 36)
	public String getScheduleTeacherA5() {
		return this.scheduleTeacherA5;
	}

	public void setScheduleTeacherA5(String scheduleTeacherA5) {
		this.scheduleTeacherA5 = scheduleTeacherA5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B1", length = 7)
	public Date getScheduleDateB1() {
		return this.scheduleDateB1;
	}

	public void setScheduleDateB1(Date scheduleDateB1) {
		this.scheduleDateB1 = scheduleDateB1;
	}

	@Column(name = "SCHEDULE_TEACHER_B1", length = 36)
	public String getScheduleTeacherB1() {
		return this.scheduleTeacherB1;
	}

	public void setScheduleTeacherB1(String scheduleTeacherB1) {
		this.scheduleTeacherB1 = scheduleTeacherB1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B2", length = 7)
	public Date getScheduleDateB2() {
		return this.scheduleDateB2;
	}

	public void setScheduleDateB2(Date scheduleDateB2) {
		this.scheduleDateB2 = scheduleDateB2;
	}

	@Column(name = "SCHEDULE_TEACHER_B2", length = 36)
	public String getScheduleTeacherB2() {
		return this.scheduleTeacherB2;
	}

	public void setScheduleTeacherB2(String scheduleTeacherB2) {
		this.scheduleTeacherB2 = scheduleTeacherB2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B4", length = 7)
	public Date getScheduleDateB4() {
		return this.scheduleDateB4;
	}

	public void setScheduleDateB4(Date scheduleDateB4) {
		this.scheduleDateB4 = scheduleDateB4;
	}

	@Column(name = "SCHEDULE_TEACHER_B4", length = 36)
	public String getScheduleTeacherB4() {
		return this.scheduleTeacherB4;
	}

	public void setScheduleTeacherB4(String scheduleTeacherB4) {
		this.scheduleTeacherB4 = scheduleTeacherB4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B5", length = 7)
	public Date getScheduleDateB5() {
		return this.scheduleDateB5;
	}

	public void setScheduleDateB5(Date scheduleDateB5) {
		this.scheduleDateB5 = scheduleDateB5;
	}

	@Column(name = "SCHEDULE_TEACHER_B5", length = 36)
	public String getScheduleTeacherB5() {
		return this.scheduleTeacherB5;
	}

	public void setScheduleTeacherB5(String scheduleTeacherB5) {
		this.scheduleTeacherB5 = scheduleTeacherB5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B6", length = 7)
	public Date getScheduleDateB6() {
		return this.scheduleDateB6;
	}

	public void setScheduleDateB6(Date scheduleDateB6) {
		this.scheduleDateB6 = scheduleDateB6;
	}

	@Column(name = "SCHEDULE_TEACHER_B6", length = 36)
	public String getScheduleTeacherB6() {
		return this.scheduleTeacherB6;
	}

	public void setScheduleTeacherB6(String scheduleTeacherB6) {
		this.scheduleTeacherB6 = scheduleTeacherB6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_B7", length = 7)
	public Date getScheduleDateB7() {
		return this.scheduleDateB7;
	}

	public void setScheduleDateB7(Date scheduleDateB7) {
		this.scheduleDateB7 = scheduleDateB7;
	}

	@Column(name = "SCHEDULE_TEACHER_B7", length = 36)
	public String getScheduleTeacherB7() {
		return this.scheduleTeacherB7;
	}

	public void setScheduleTeacherB7(String scheduleTeacherB7) {
		this.scheduleTeacherB7 = scheduleTeacherB7;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C1", length = 7)
	public Date getScheduleDateC1() {
		return this.scheduleDateC1;
	}

	public void setScheduleDateC1(Date scheduleDateC1) {
		this.scheduleDateC1 = scheduleDateC1;
	}

	@Column(name = "SCHEDULE_TEACHER_C1", length = 36)
	public String getScheduleTeacherC1() {
		return this.scheduleTeacherC1;
	}

	public void setScheduleTeacherC1(String scheduleTeacherC1) {
		this.scheduleTeacherC1 = scheduleTeacherC1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C2", length = 7)
	public Date getScheduleDateC2() {
		return this.scheduleDateC2;
	}

	public void setScheduleDateC2(Date scheduleDateC2) {
		this.scheduleDateC2 = scheduleDateC2;
	}

	@Column(name = "SCHEDULE_TEACHER_C2", length = 36)
	public String getScheduleTeacherC2() {
		return this.scheduleTeacherC2;
	}

	public void setScheduleTeacherC2(String scheduleTeacherC2) {
		this.scheduleTeacherC2 = scheduleTeacherC2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C3", length = 7)
	public Date getScheduleDateC3() {
		return this.scheduleDateC3;
	}

	public void setScheduleDateC3(Date scheduleDateC3) {
		this.scheduleDateC3 = scheduleDateC3;
	}

	@Column(name = "SCHEDULE_TEACHER_C3", length = 36)
	public String getScheduleTeacherC3() {
		return this.scheduleTeacherC3;
	}

	public void setScheduleTeacherC3(String scheduleTeacherC3) {
		this.scheduleTeacherC3 = scheduleTeacherC3;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C4", length = 7)
	public Date getScheduleDateC4() {
		return this.scheduleDateC4;
	}

	public void setScheduleDateC4(Date scheduleDateC4) {
		this.scheduleDateC4 = scheduleDateC4;
	}

	@Column(name = "SCHEDULE_TEACHER_C4", length = 36)
	public String getScheduleTeacherC4() {
		return this.scheduleTeacherC4;
	}

	public void setScheduleTeacherC4(String scheduleTeacherC4) {
		this.scheduleTeacherC4 = scheduleTeacherC4;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C5", length = 7)
	public Date getScheduleDateC5() {
		return this.scheduleDateC5;
	}

	public void setScheduleDateC5(Date scheduleDateC5) {
		this.scheduleDateC5 = scheduleDateC5;
	}

	@Column(name = "SCHEDULE_TEACHER_C5", length = 36)
	public String getScheduleTeacherC5() {
		return this.scheduleTeacherC5;
	}

	public void setScheduleTeacherC5(String scheduleTeacherC5) {
		this.scheduleTeacherC5 = scheduleTeacherC5;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C6", length = 7)
	public Date getScheduleDateC6() {
		return this.scheduleDateC6;
	}

	public void setScheduleDateC6(Date scheduleDateC6) {
		this.scheduleDateC6 = scheduleDateC6;
	}

	@Column(name = "SCHEDULE_TEACHER_C6", length = 36)
	public String getScheduleTeacherC6() {
		return this.scheduleTeacherC6;
	}

	public void setScheduleTeacherC6(String scheduleTeacherC6) {
		this.scheduleTeacherC6 = scheduleTeacherC6;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCHEDULE_DATE_C7", length = 7)
	public Date getScheduleDateC7() {
		return this.scheduleDateC7;
	}

	public void setScheduleDateC7(Date scheduleDateC7) {
		this.scheduleDateC7 = scheduleDateC7;
	}

	@Column(name = "SCHEDULE_TEACHER_C7", length = 36)
	public String getScheduleTeacherC7() {
		return this.scheduleTeacherC7;
	}

	public void setScheduleTeacherC7(String scheduleTeacherC7) {
		this.scheduleTeacherC7 = scheduleTeacherC7;
	}

	@Column(name = "APPROVE_A", length = 1)
	public String getApproveA() {
		return this.approveA;
	}

	public void setApproveA(String approveA) {
		this.approveA = approveA;
	}

	@Column(name = "APPROVE_SIGNATURE_A", length = 36)
	public String getApproveSignatureA() {
		return this.approveSignatureA;
	}

	public void setApproveSignatureA(String approveSignatureA) {
		this.approveSignatureA = approveSignatureA;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVE_DATE_A", length = 7)
	public Date getApproveDateA() {
		return this.approveDateA;
	}

	public void setApproveDateA(Date approveDateA) {
		this.approveDateA = approveDateA;
	}

	@Column(name = "APPROVE_B", length = 1)
	public String getApproveB() {
		return this.approveB;
	}

	public void setApproveB(String approveB) {
		this.approveB = approveB;
	}

	@Column(name = "APPROVE_SIGNATURE_B", length = 36)
	public String getApproveSignatureB() {
		return this.approveSignatureB;
	}

	public void setApproveSignatureB(String approveSignatureB) {
		this.approveSignatureB = approveSignatureB;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVE_DATE_B", length = 7)
	public Date getApproveDateB() {
		return this.approveDateB;
	}

	public void setApproveDateB(Date approveDateB) {
		this.approveDateB = approveDateB;
	}

	@Column(name = "APPROVE_C", length = 1)
	public String getApproveC() {
		return this.approveC;
	}

	public void setApproveC(String approveC) {
		this.approveC = approveC;
	}

	@Column(name = "APPROVE_SIGNATURE_C", length = 36)
	public String getApproveSignatureC() {
		return this.approveSignatureC;
	}

	public void setApproveSignatureC(String approveSignatureC) {
		this.approveSignatureC = approveSignatureC;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVE_DATE_C", length = 7)
	public Date getApproveDateC() {
		return this.approveDateC;
	}

	public void setApproveDateC(Date approveDateC) {
		this.approveDateC = approveDateC;
	}

}