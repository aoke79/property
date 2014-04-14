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
 * 飞行员技术检查报告 entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_INSPECTION_REPORT")
public class TfQualInspectionReport implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// id
	private String reportid;
	// 技术负责人签名
	private TfQualBaseSignature technicalDirector;
	// 监察、检查员签名
	private TfQualBaseSignature inspector;
	//监察、检查员签名  刘思源代签字段
	private String inspectorName;
	// 飞行员签名
//	private TfQualBaseSignature pilot;
	private String pilot;
	// 局方签名
	private TfQualBaseSignature bureau;
	// 申报人员明细
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	// 签名（公司）签名
	private TfQualBaseSignature subsidiarySign;
	// 检查目的
	private String ipurpose;
	// 航线（站）
	private String route;
	// 最低或模拟天气实况（日/夜）,取值为 D/N（表示day/night）
	private String dayornight;
	// 最低或模拟天气实况
	private String weather;
	// 飞行时间（小时）
	private short timehour;
	// 飞行时间（分钟）
	private byte timeminute;
	// 飞行时间（次数）
	private byte frequency;
	// 检查员评语意见
	private String comments;
	// 签字日期（监察、检查员）
	private Date inspectordate;
	// 签字日期（飞行员）
	private Date pilotdate;
	// 分、子公司、飞行总队（部）或单位意见
	private String subsidiarycomm;
	// 日期（公司）
	private Date subsidiarydate;
	// 技术负责人意见
	private String technicaldirectorcomm;
	// 日期（技术负责人）
	private Date technicaldirectordate;
	// 局方意见
	private String bureaucomm;
	// 日期（局方）
	private Date bureaudate;
	//检查报告表类型(jcbg:技术检查报告，bcxl:本场训练单)
	private String reporttype;
	//用于本场单  最后修改人
	private String modifier;
	//技术检查报告成绩 set
	private Set<TfQualInspectionReportD> tfQualInspectionReportDs = new HashSet<TfQualInspectionReportD>(
			0);

	// Constructors

	/** default constructor */
	public TfQualInspectionReport() {
	}

	/** full constructor */
	public TfQualInspectionReport(String reportid,
			TfQualBaseSignature technicalDirector,
			TfQualBaseSignature inspector, String pilot,
			TfQualBaseSignature bureau, TfQualDeclaraPilot tfQualDeclaraPilot,
			TfQualBaseSignature subsidiarySign, String ipurpose, String route,
			String dayornight, String weather, short timehour, byte timeminute,
			byte frequency, String comments, Date inspectordate,
			Date pilotdate, String subsidiarycomm, Date subsidiarydate,
			String technicaldirectorcomm, Date technicaldirectordate,
			String bureaucomm, Date bureaudate,String reporttype,String modifier,
			Set<TfQualInspectionReportD> tfQualInspectionReportDs,String inspectorName) {
		super();
		this.reportid = reportid;
		this.technicalDirector = technicalDirector;
		this.inspector = inspector;
		this.pilot = pilot;
		this.bureau = bureau;
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.subsidiarySign = subsidiarySign;
		this.ipurpose = ipurpose;
		this.route = route;
		this.dayornight = dayornight;
		this.weather = weather;
		this.timehour = timehour;
		this.timeminute = timeminute;
		this.frequency = frequency;
		this.comments = comments;
		this.inspectordate = inspectordate;
		this.pilotdate = pilotdate;
		this.subsidiarycomm = subsidiarycomm;
		this.subsidiarydate = subsidiarydate;
		this.technicaldirectorcomm = technicaldirectorcomm;
		this.technicaldirectordate = technicaldirectordate;
		this.bureaucomm = bureaucomm;
		this.bureaudate = bureaudate;
		this.reporttype=reporttype;
		this.tfQualInspectionReportDs = tfQualInspectionReportDs;
		this.inspectorName = inspectorName;
		this.modifier = modifier;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "REPORTID", unique = true, nullable = false, length = 36)
	public String getReportid() {
		return this.reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TECHNICALDIRECTOR")
	public TfQualBaseSignature getTechnicalDirector() {
		return this.technicalDirector;
	}

	public void setTechnicalDirector(TfQualBaseSignature technicaldirector) {
		this.technicalDirector = technicaldirector;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSPECTOR")
	public TfQualBaseSignature getInspector() {
		return this.inspector;
	}

	public void setInspector(
			TfQualBaseSignature inspector) {
		this.inspector = inspector;
	}

	@Column(name = "PILOT",length= 36)
	public String getPilot() {
		return this.pilot;
	}

	public void setPilot( String pilot) {
		this.pilot = pilot;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BUREAU")
	public TfQualBaseSignature getBureau() {
		return this.bureau;
	}

	public void setBureau(
			TfQualBaseSignature bureau) {
		this.bureau = bureau;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DETAILSID")
	public TfQualDeclaraPilot getTfQualDeclaraPilot() {
		return this.tfQualDeclaraPilot;
	}

	public void setTfQualDeclaraPilot(TfQualDeclaraPilot tfQualDeclaraPilot) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBSIDIARYSIGN")
	public TfQualBaseSignature getSubsidiarySign() {
		return this.subsidiarySign;
	}

	public void setSubsidiarySign(
			TfQualBaseSignature subsidiarySign) {
		this.subsidiarySign = subsidiarySign;
	}

	@Column(name = "IPURPOSE", length = 100)
	public String getIpurpose() {
		return this.ipurpose;
	}

	public void setIpurpose(String ipurpose) {
		this.ipurpose = ipurpose;
	}

	@Column(name = "ROUTE", length = 50)
	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Column(name = "DAYORNIGHT", length = 1)
	public String getDayornight() {
		return this.dayornight;
	}

	public void setDayornight(String dayornight) {
		this.dayornight = dayornight;
	}

	@Column(name = "WEATHER", length = 10)
	public String getWeather() {
		return this.weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@Column(name = "TIMEHOUR", precision = 3, scale = 0)
	public short getTimehour() {
		return this.timehour;
	}

	public void setTimehour(short timehour) {
		this.timehour = timehour;
	}

	@Column(name = "TIMEMINUTE", precision = 2, scale = 0)
	public byte getTimeminute() {
		return this.timeminute;
	}

	public void setTimeminute(byte timeminute) {
		this.timeminute = timeminute;
	}

	@Column(name = "FREQUENCY", precision = 2, scale = 0)
	public byte getFrequency() {
		return this.frequency;
	}

	public void setFrequency(byte frequency) {
		this.frequency = frequency;
	}

	@Column(name = "COMMENTS", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSPECTORDATE", length = 7)
	public Date getInspectordate() {
		return this.inspectordate;
	}

	public void setInspectordate(Date inspectordate) {
		this.inspectordate = inspectordate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PILOTDATE", length = 7)
	public Date getPilotdate() {
		return this.pilotdate;
	}

	public void setPilotdate(Date pilotdate) {
		this.pilotdate = pilotdate;
	}

	@Column(name = "SUBSIDIARYCOMM", length = 500)
	public String getSubsidiarycomm() {
		return this.subsidiarycomm;
	}

	public void setSubsidiarycomm(String subsidiarycomm) {
		this.subsidiarycomm = subsidiarycomm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBSIDIARYDATE", length = 7)
	public Date getSubsidiarydate() {
		return this.subsidiarydate;
	}

	public void setSubsidiarydate(Date subsidiarydate) {
		this.subsidiarydate = subsidiarydate;
	}

	@Column(name = "TECHNICALDIRECTORCOMM", length = 500)
	public String getTechnicaldirectorcomm() {
		return this.technicaldirectorcomm;
	}

	public void setTechnicaldirectorcomm(String technicaldirectorcomm) {
		this.technicaldirectorcomm = technicaldirectorcomm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TECHNICALDIRECTORDATE", length = 7)
	public Date getTechnicaldirectordate() {
		return this.technicaldirectordate;
	}

	public void setTechnicaldirectordate(Date technicaldirectordate) {
		this.technicaldirectordate = technicaldirectordate;
	}

	@Column(name = "BUREAUCOMM", length = 500)
	public String getBureaucomm() {
		return this.bureaucomm;
	}

	public void setBureaucomm(String bureaucomm) {
		this.bureaucomm = bureaucomm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BUREAUDATE", length = 7)
	public Date getBureaudate() {
		return this.bureaudate;
	}

	public void setBureaudate(Date bureaudate) {
		this.bureaudate = bureaudate;
	}

	@Column(name = "REPORTTYPE", length = 5)
	public String getReporttype() {
		return this.reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tfQualInspectionReport")
	public Set<TfQualInspectionReportD> getTfQualInspectionReportDs() {
		return this.tfQualInspectionReportDs;
	}

	public void setTfQualInspectionReportDs(
			Set<TfQualInspectionReportD> tfQualInspectionReportDs) {
		this.tfQualInspectionReportDs = tfQualInspectionReportDs;
	}
	@Column(name = "INSPECTOR_BASE", length = 30)
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	@Column(name = "MODIFIER", length = 36)
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	

}