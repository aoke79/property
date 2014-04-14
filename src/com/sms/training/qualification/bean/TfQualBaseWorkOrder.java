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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 考试工作单  entity. 
 * @author licumn
 */
@Entity
@Table(name = "TF_QUAL_BASE_WORK_ORDER")
public class TfQualBaseWorkOrder implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	//飞行员  hrid
	private String pilotid;
	//航空器型号
	private String hkqType;
	//航空器注册号
	private String hkqRegistCode;
	//CAAC模拟机编号
	private String caacMnCode;
	//CAAC模拟机级别
	private String caacMnType;
	//CAAC训练器编号
	private String caacXlCode;
	//CAAC训练器级别
	private String caacXlType;
	//考试员评语及结论
	private String ksyComment;
	//通过或不通过
	private String examResult;
	//考试员合格证编号
	private String ksyNum;
	//考试员ID
	private String ksyCode;
	//考试员签字日期
	private Date ksyDate;
	//监察员意见  同意不同意
	private String jcyYj;
	//监察员ID
	private String jcyCode;
	//监察员签字日期
	private Date jcyDate;
	//运行基地
	private String runbase;
	//考试开始时间
	private Date ksStartdt;
	//考试截止时间
	private Date ksEnddt;
	//地点
	private String location;
	//科目
	private String subject;
	//最后修改人
	private String modifier;
	//考试项目/综合评估项目
	private Set<TfQualBaseWorkOrderItem> tfQualBaseWorkOrderItems = new HashSet<TfQualBaseWorkOrderItem>(
			0);

	// Constructors

	/** default constructor */
	public TfQualBaseWorkOrder() {
	}

	/** full constructor */
	public TfQualBaseWorkOrder(TfQualDeclaraPilot tfQualDeclaraPilot,
			String pilotid, String hkqType, String hkqRegistCode,
			String caacMnCode, String caacMnType, String caacXlCode,
			String caacXlType, String ksyComment, String examResult,
			String ksyCode, Date ksyDate, String jcyYj, String jcyCode,
			Date jcyDate, String runbase, Date ksStartdt, Date ksEnddt,
			String location, String subject, String ksyNum,String modifier,
			Set<TfQualBaseWorkOrderItem> tfQualBaseWorkOrderItems) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.pilotid = pilotid;
		this.hkqType = hkqType;
		this.hkqRegistCode = hkqRegistCode;
		this.caacMnCode = caacMnCode;
		this.caacMnType = caacMnType;
		this.caacXlCode = caacXlCode;
		this.caacXlType = caacXlType;
		this.ksyComment = ksyComment;
		this.examResult = examResult;
		this.ksyCode = ksyCode;
		this.ksyDate = ksyDate;
		this.jcyYj = jcyYj;
		this.jcyCode = jcyCode;
		this.jcyDate = jcyDate;
		this.runbase = runbase;
		this.ksStartdt = ksStartdt;
		this.ksEnddt = ksEnddt;
		this.location = location;
		this.subject = subject;
		this.ksyNum = ksyNum;
		this.modifier = modifier;
		this.tfQualBaseWorkOrderItems = tfQualBaseWorkOrderItems;
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
	@JoinColumn(name = "DETAILSID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@Column(name = "PILOTID", length = 36)
	public String getPilotid() {
		return this.pilotid;
	}

	public void setPilotid(String pilotid) {
		this.pilotid = pilotid;
	}

	@Column(name = "HKQ_TYPE", length = 50)
	public String getHkqType() {
		return this.hkqType;
	}

	public void setHkqType(String hkqType) {
		this.hkqType = hkqType;
	}

	@Column(name = "HKQ_REGIST_CODE", length = 100)
	public String getHkqRegistCode() {
		return this.hkqRegistCode;
	}

	public void setHkqRegistCode(String hkqRegistCode) {
		this.hkqRegistCode = hkqRegistCode;
	}

	@Column(name = "CAAC_MN_CODE", length = 100)
	public String getCaacMnCode() {
		return this.caacMnCode;
	}

	public void setCaacMnCode(String caacMnCode) {
		this.caacMnCode = caacMnCode;
	}

	@Column(name = "CAAC_MN_TYPE", length = 50)
	public String getCaacMnType() {
		return this.caacMnType;
	}

	public void setCaacMnType(String caacMnType) {
		this.caacMnType = caacMnType;
	}

	@Column(name = "CAAC_XL_CODE", length = 100)
	public String getCaacXlCode() {
		return this.caacXlCode;
	}

	public void setCaacXlCode(String caacXlCode) {
		this.caacXlCode = caacXlCode;
	}

	@Column(name = "CAAC_XL_TYPE", length = 50)
	public String getCaacXlType() {
		return this.caacXlType;
	}

	public void setCaacXlType(String caacXlType) {
		this.caacXlType = caacXlType;
	}

	@Column(name = "KSY_COMMENT", length = 200)
	public String getKsyComment() {
		return this.ksyComment;
	}

	public void setKsyComment(String ksyComment) {
		this.ksyComment = ksyComment;
	}

	@Column(name = "EXAM_RESULT", length = 50)
	public String getExamResult() {
		return this.examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	@Column(name = "KSY_CODE", length = 50)
	public String getKsyCode() {
		return this.ksyCode;
	}

	public void setKsyCode(String ksyCode) {
		this.ksyCode = ksyCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KSY_DATE", length = 7)
	public Date getKsyDate() {
		return this.ksyDate;
	}

	public void setKsyDate(Date ksyDate) {
		this.ksyDate = ksyDate;
	}

	@Column(name = "JCY_YJ", length = 2)
	public String getJcyYj() {
		return this.jcyYj;
	}

	public void setJcyYj(String jcyYj) {
		this.jcyYj = jcyYj;
	}

	@Column(name = "JCY_CODE", length = 36)
	public String getJcyCode() {
		return this.jcyCode;
	}

	public void setJcyCode(String jcyCode) {
		this.jcyCode = jcyCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JCY_DATE", length = 7)
	public Date getJcyDate() {
		return this.jcyDate;
	}

	public void setJcyDate(Date jcyDate) {
		this.jcyDate = jcyDate;
	}

	@Column(name = "RUNBASE", length = 60)
	public String getRunbase() {
		return this.runbase;
	}

	public void setRunbase(String runbase) {
		this.runbase = runbase;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KS_STARTDT", length = 7)
	public Date getKsStartdt() {
		return this.ksStartdt;
	}

	public void setKsStartdt(Date ksStartdt) {
		this.ksStartdt = ksStartdt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KS_ENDDT", length = 7)
	public Date getKsEnddt() {
		return this.ksEnddt;
	}

	public void setKsEnddt(Date ksEnddt) {
		this.ksEnddt = ksEnddt;
	}

	@Column(name = "LOCATION", length = 60)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "SUBJECT", length = 1)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "KSY_NUM", length = 50)
	public String getKsyNum() {
		return this.ksyNum;
	}

	public void setKsyNum(String ksyNum) {
		this.ksyNum = ksyNum;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tfQualBaseWorkOrder")
	public Set<TfQualBaseWorkOrderItem> getTfQualBaseWorkOrderItems() {
		return this.tfQualBaseWorkOrderItems;
	}

	public void setTfQualBaseWorkOrderItems(
			Set<TfQualBaseWorkOrderItem> tfQualBaseWorkOrderItems) {
		this.tfQualBaseWorkOrderItems = tfQualBaseWorkOrderItems;
	}

	@Column(name = "MODIFIER", length = 36)
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}