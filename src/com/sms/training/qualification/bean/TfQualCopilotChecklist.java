package com.sms.training.qualification.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 副驾驶资格检查工作单 entity. 
 * @author pxp
 */
@Entity
@Table(name = "TF_QUAL_COPILOT_CHECKLIST")
public class TfQualCopilotChecklist implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	//ID
	private String id;
	//申报人员信息
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//运行基地
	private String runBase;
	//检查日期
	private Date checkDate;
	//检查地点
	private String checkLocation;
	//检查设备
	private String checkDevice;
	//航空器型号
	private String aircraftModel;
	//飞机注册号
	private String registCode;
	//CAAC模拟机合格证编号
	private String simulatorCertificateNo;
	//熟悉发动机、设备和系统操作程序
	private String checkItem11;
	//熟悉性能和限制
	private String checkItem12;
	//熟悉正常、非正常和应急操作程序
	private String checkItem13;
	//熟悉经批准的运行手册
	private String checkItem14;
	//熟悉标牌与标志
	private String checkItem15;
	//独立操纵航空器完成起飞、着陆
	private String checkItem2;
	//发动机停车后的处置程序
	private String checkItem3;
	//机组资源管理训练
	private String checkItem4;
	//考试员评语
	private String examinerComments;
	//结论
	private String examinerResult;
	//考试员合格证编号
	private String examinerCertificateNo;
	//期满日期
	private Date expiryDate;
	//考试员签字
	private String examinerSignature;
	//考试员签字日期
	private Date examinerSignDate;
	//所在地区管理局
	private String areaManagementBureau;
	//监察员审查意见
	private String supervisorsReviewResult;
	//监察员签字
	private String supervisorsSignature;
	//监察员签字日期
	private Date supervisorsSignDate;

	// Constructors

	/** default constructor */
	public TfQualCopilotChecklist() {
	}

	/** full constructor */
	public TfQualCopilotChecklist(TfQualDeclaraPilot tfQualDeclaraPilot,
			String runBase, Date checkDate, String checkLocation,
			String checkDevice, String aircraftModel, String registCode,
			String simulatorCertificateNo, String checkItem11,
			String checkItem12, String checkItem13, String checkItem14,
			String checkItem15, String checkItem2, String checkItem3,
			String checkItem4, String examinerComments, String examinerResult,
			String examinerCertificateNo, Date expiryDate,
			String examinerSignature, Date examinerSignDate,
			String areaManagementBureau, String supervisorsReviewResult,
			String supervisorsSignature, Date supervisorsSignDate) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.runBase = runBase;
		this.checkDate = checkDate;
		this.checkLocation = checkLocation;
		this.checkDevice = checkDevice;
		this.aircraftModel = aircraftModel;
		this.registCode = registCode;
		this.simulatorCertificateNo = simulatorCertificateNo;
		this.checkItem11 = checkItem11;
		this.checkItem12 = checkItem12;
		this.checkItem13 = checkItem13;
		this.checkItem14 = checkItem14;
		this.checkItem15 = checkItem15;
		this.checkItem2 = checkItem2;
		this.checkItem3 = checkItem3;
		this.checkItem4 = checkItem4;
		this.examinerComments = examinerComments;
		this.examinerResult = examinerResult;
		this.examinerCertificateNo = examinerCertificateNo;
		this.expiryDate = expiryDate;
		this.examinerSignature = examinerSignature;
		this.examinerSignDate = examinerSignDate;
		this.areaManagementBureau = areaManagementBureau;
		this.supervisorsReviewResult = supervisorsReviewResult;
		this.supervisorsSignature = supervisorsSignature;
		this.supervisorsSignDate = supervisorsSignDate;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILS_ID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@Column(name = "RUN_BASE", length = 60)
	public String getRunBase() {
		return this.runBase;
	}

	public void setRunBase(String runBase) {
		this.runBase = runBase;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE", length = 7)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "CHECK_LOCATION", length = 60)
	public String getCheckLocation() {
		return this.checkLocation;
	}

	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}

	@Column(name = "CHECK_DEVICE", length = 3)
	public String getCheckDevice() {
		return this.checkDevice;
	}

	public void setCheckDevice(String checkDevice) {
		this.checkDevice = checkDevice;
	}

	@Column(name = "AIRCRAFT_MODEL", length = 20)
	public String getAircraftModel() {
		return this.aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	@Column(name = "REGIST_CODE", length = 10)
	public String getRegistCode() {
		return this.registCode;
	}

	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}

	@Column(name = "SIMULATOR_CERTIFICATE_NO", length = 20)
	public String getSimulatorCertificateNo() {
		return this.simulatorCertificateNo;
	}

	public void setSimulatorCertificateNo(String simulatorCertificateNo) {
		this.simulatorCertificateNo = simulatorCertificateNo;
	}

	@Column(name = "CHECK_ITEM11", length = 1)
	public String getCheckItem11() {
		return this.checkItem11;
	}

	public void setCheckItem11(String checkItem11) {
		this.checkItem11 = checkItem11;
	}

	@Column(name = "CHECK_ITEM12", length = 1)
	public String getCheckItem12() {
		return this.checkItem12;
	}

	public void setCheckItem12(String checkItem12) {
		this.checkItem12 = checkItem12;
	}

	@Column(name = "CHECK_ITEM13", length = 1)
	public String getCheckItem13() {
		return this.checkItem13;
	}

	public void setCheckItem13(String checkItem13) {
		this.checkItem13 = checkItem13;
	}

	@Column(name = "CHECK_ITEM14", length = 1)
	public String getCheckItem14() {
		return this.checkItem14;
	}

	public void setCheckItem14(String checkItem14) {
		this.checkItem14 = checkItem14;
	}

	@Column(name = "CHECK_ITEM15", length = 1)
	public String getCheckItem15() {
		return this.checkItem15;
	}

	public void setCheckItem15(String checkItem15) {
		this.checkItem15 = checkItem15;
	}

	@Column(name = "CHECK_ITEM2", length = 1)
	public String getCheckItem2() {
		return this.checkItem2;
	}

	public void setCheckItem2(String checkItem2) {
		this.checkItem2 = checkItem2;
	}

	@Column(name = "CHECK_ITEM3", length = 1)
	public String getCheckItem3() {
		return this.checkItem3;
	}

	public void setCheckItem3(String checkItem3) {
		this.checkItem3 = checkItem3;
	}

	@Column(name = "CHECK_ITEM4", length = 1)
	public String getCheckItem4() {
		return this.checkItem4;
	}

	public void setCheckItem4(String checkItem4) {
		this.checkItem4 = checkItem4;
	}

	@Column(name = "EXAMINER_COMMENTS", length = 150)
	public String getExaminerComments() {
		return this.examinerComments;
	}

	public void setExaminerComments(String examinerComments) {
		this.examinerComments = examinerComments;
	}

	@Column(name = "EXAMINER_RESULT", length = 1)
	public String getExaminerResult() {
		return this.examinerResult;
	}

	public void setExaminerResult(String examinerResult) {
		this.examinerResult = examinerResult;
	}

	@Column(name = "EXAMINER_CERTIFICATE_NO", length = 15)
	public String getExaminerCertificateNo() {
		return this.examinerCertificateNo;
	}

	public void setExaminerCertificateNo(String examinerCertificateNo) {
		this.examinerCertificateNo = examinerCertificateNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE", length = 7)
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "EXAMINER_SIGNATURE", length = 36)
	public String getExaminerSignature() {
		return this.examinerSignature;
	}

	public void setExaminerSignature(String examinerSignature) {
		this.examinerSignature = examinerSignature;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXAMINER_SIGN_DATE", length = 7)
	public Date getExaminerSignDate() {
		return this.examinerSignDate;
	}

	public void setExaminerSignDate(Date examinerSignDate) {
		this.examinerSignDate = examinerSignDate;
	}

	@Column(name = "AREA_MANAGEMENT_BUREAU", length = 2)
	public String getAreaManagementBureau() {
		return this.areaManagementBureau;
	}

	public void setAreaManagementBureau(String areaManagementBureau) {
		this.areaManagementBureau = areaManagementBureau;
	}

	@Column(name = "SUPERVISORS_REVIEW_RESULT", length = 1)
	public String getSupervisorsReviewResult() {
		return this.supervisorsReviewResult;
	}

	public void setSupervisorsReviewResult(String supervisorsReviewResult) {
		this.supervisorsReviewResult = supervisorsReviewResult;
	}

	@Column(name = "SUPERVISORS_SIGNATURE", length = 36)
	public String getSupervisorsSignature() {
		return this.supervisorsSignature;
	}

	public void setSupervisorsSignature(String supervisorsSignature) {
		this.supervisorsSignature = supervisorsSignature;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SUPERVISORS_SIGN_DATE", length = 7)
	public Date getSupervisorsSignDate() {
		return this.supervisorsSignDate;
	}

	public void setSupervisorsSignDate(Date supervisorsSignDate) {
		this.supervisorsSignDate = supervisorsSignDate;
	}

}