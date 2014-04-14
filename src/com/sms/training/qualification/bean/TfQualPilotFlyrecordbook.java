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
 * 飞行经历记录本 entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_FLYRECORDBOOK")
public class TfQualPilotFlyrecordbook implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	/** id */
	private String recordId;
	/**申报人员明细 */
	//private String detailsId;
	private TfQualDeclaraPilot tfQualDeclaraPilot;
	/**飞行员ID */
	private String pilotId;
	/**日期 */
	private Date recordDate;
	/**航空器型别 */
	private String typeA;
	/**航空器登记号 */
	private String aircraftId;
	/**起飞站 */
	private String siteFrom;
	/**降落站 */
	private String siteTo;
	/**出发时刻 */
	private String dep;
	/**到达时刻 */
	private String arr;
	/**飞行科目 */
	private String flyingSubjects;
	/**着陆次数（夜间） */
	private String nologNight;
	/** 着陆次数（昼间）*/
	private String nologDay;
	/** 驾驶员飞行时间*/
	private String pilotFlightTime;
	/**机长 */
	private String pic;
	/**单飞 */
	private String solo;
	/**转场（机长） */
	private String crossCountryPic;
	/** 夜间（机长）*/
	private String nightPic;
	/**副驾驶 */
	private String copilot;
	/** 带飞（副驾驶）*/
	private String dual;
	/**转场（副驾驶） */
	private String crossCountryCopilot;
	/**夜间（副驾驶） */
	private String nightCopilot;
	/** 模拟仪表*/
	private String simulated;
	/** 真实仪表*/
	private String actual;
	/**训练地点 */
	private String location;
	/** 模拟机时间*/
	private String simulator;
	/**训练器时间 */
	private String trainer;
	/** 教员类型*/
	private String instructor;
	/**教员时间 */
	private String instructorTime;
	/** 领航员*/
	private String navigator;
	/**通信员 */
	private String radioOperator;
	/**机械员 */
	private String engineer;
	/**机长、教员签字证明 */
	private String picOrInstructorSig;
	/** 备注*/
	private String remarks;
	/** 创建人*/
	private String creater;
	//

	// Constructors

	/** default constructor */
	public TfQualPilotFlyrecordbook() {
	}

	/** full constructor */
	public TfQualPilotFlyrecordbook(TfQualDeclaraPilot tfQualDeclaraPilot,
			String pilotId, Date recordDate, String typeA, String aircraftId,
			String siteFrom, String siteTo, String dep, String arr,
			String flyingSubjects, String nologNight, String nologDay,
			String pilotFlightTime, String pic, String solo, String crossCountryPic,
			String nightPic, String copilot, String dual, String crossCountryCopilot,
			String nightCopilot, String simulated, String actual, String location,
			String simulator, String trainer, String instructor,
			String instructorTime, String navigator, String radioOperator,
			String engineer, String picOrInstructorSig, String remarks,String creater) {
		this.tfQualDeclaraPilot = tfQualDeclaraPilot;
		this.pilotId = pilotId;
		this.recordDate = recordDate;
		this.typeA = typeA;
		this.aircraftId = aircraftId;
		this.siteFrom = siteFrom;
		this.siteTo = siteTo;
		this.dep = dep;
		this.arr = arr;
		this.flyingSubjects = flyingSubjects;
		this.nologNight = nologNight;
		this.nologDay = nologDay;
		this.pilotFlightTime = pilotFlightTime;
		this.pic = pic;
		this.solo = solo;
		this.crossCountryPic = crossCountryPic;
		this.nightPic = nightPic;
		this.copilot = copilot;
		this.dual = dual;
		this.crossCountryCopilot = crossCountryCopilot;
		this.nightCopilot = nightCopilot;
		this.simulated = simulated;
		this.actual = actual;
		this.location = location;
		this.simulator = simulator;
		this.trainer = trainer;
		this.instructor = instructor;
		this.instructorTime = instructorTime;
		this.navigator = navigator;
		this.radioOperator = radioOperator;
		this.engineer = engineer;
		this.picOrInstructorSig = picOrInstructorSig;
		this.remarks = remarks;
		this.creater = creater;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "RECORDID", unique = true, nullable = false, length = 36)
	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
	public String getPilotId() {
		return this.pilotId;
	}

	public void setPilotId(String pilotId) {
		this.pilotId = pilotId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RECORDDATE", length = 7)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "TYPEA", length = 50)
	public String getTypeA() {
		return this.typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	@Column(name = "AIRCRAFTID", length = 50)
	public String getAircraftId() {
		return this.aircraftId;
	}

	public void setAircraftId(String aircraftId) {
		this.aircraftId = aircraftId;
	}

	@Column(name = "SITEFROM", length = 50)
	public String getSiteFrom() {
		return this.siteFrom;
	}

	public void setSiteFrom(String siteFrom) {
		this.siteFrom = siteFrom;
	}

	@Column(name = "SITETO", length = 50)
	public String getSiteTo() {
		return this.siteTo;
	}

	public void setSiteTo(String siteTo) {
		this.siteTo = siteTo;
	}

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEP", length = 5)
	public String getDep() {
		return this.dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ARR", length = 5)
	public String getArr() {
		return this.arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	@Column(name = "FLYINGSUBJECTS", length = 50)
	public String getFlyingSubjects() {
		return this.flyingSubjects;
	}

	public void setFlyingSubjects(String flyingSubjects) {
		this.flyingSubjects = flyingSubjects;
	}

	@Column(name = "NOLOGNIGHT", length = 2 )
	public String getNologNight() {
		return this.nologNight;
	}

	public void setNologNight(String nologNight) {
		this.nologNight = nologNight;
	}

	@Column(name = "NOLOGDAY", length = 2 )
	public String getNologDay() {
		return this.nologDay;
	}

	public void setNologDay(String nologDay) {
		this.nologDay = nologDay;
	}

	@Column(name = "PILOTFLIGHTTIME", length = 10 )
	public String getPilotFlightTime() {
		return this.pilotFlightTime;
	}

	public void setPilotFlightTime(String pilotFlightTime) {
		this.pilotFlightTime = pilotFlightTime;
	}

	@Column(name = "PIC", length = 10 )
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "SOLO", length = 10 )
	public String getSolo() {
		return this.solo;
	}

	public void setSolo(String solo) {
		this.solo = solo;
	}

	@Column(name = "CROSSCOUNTRYPIC", length = 10 )
	public String getCrossCountryPic() {
		return this.crossCountryPic;
	}

	public void setCrossCountryPic(String crossCountryPic) {
		this.crossCountryPic = crossCountryPic;
	}

	@Column(name = "NIGHTPIC", length = 10 )
	public String getNightPic() {
		return this.nightPic;
	}

	public void setNightPic(String nightPic) {
		this.nightPic = nightPic;
	}

	@Column(name = "COPILOT", length = 10 )
	public String getCopilot() {
		return this.copilot;
	}

	public void setCopilot(String copilot) {
		this.copilot = copilot;
	}

	@Column(name = "DUAL", length = 10 )
	public String getDual() {
		return this.dual;
	}

	public void setDual(String dual) {
		this.dual = dual;
	}

	@Column(name = "CROSSCOUNTRYCOPILOT", length = 10 )
	public String getCrossCountryCopilot() {
		return this.crossCountryCopilot;
	}

	public void setCrossCountryCopilot(String crossCountryCopilot) {
		this.crossCountryCopilot = crossCountryCopilot;
	}

	@Column(name = "NIGHTCOPILOT", length = 10 )
	public String getNightCopilot() {
		return this.nightCopilot;
	}

	public void setNightCopilot(String nightCopilot) {
		this.nightCopilot = nightCopilot;
	}

	@Column(name = "SIMULATED", length = 50)
	public String getSimulated() {
		return this.simulated;
	}

	public void setSimulated(String simulated) {
		this.simulated = simulated;
	}

	@Column(name = "ACTUAL", length = 50)
	public String getActual() {
		return this.actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	@Column(name = "LOCATION", length = 50)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String loction) {
		this.location = loction;
	}

	@Column(name = "SIMULATOR", length = 10 )
	public String getSimulator() {
		return this.simulator;
	}

	public void setSimulator(String simulator) {
		this.simulator = simulator;
	}

	@Column(name = "TRAINER", length = 10 )
	public String getTrainer() {
		return this.trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	@Column(name = "INSTRUCTOR", length = 50)
	public String getInstructor() {
		return this.instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	@Column(name = "INSTRUCTORTIME", length = 10 )
	public String getInstructorTime() {
		return this.instructorTime;
	}

	public void setInstructorTime(String instructorTime) {
		this.instructorTime = instructorTime;
	}

	@Column(name = "NAVIGATOR", length = 50)
	public String getNavigator() {
		return this.navigator;
	}

	public void setNavigator(String navigator) {
		this.navigator = navigator;
	}

	@Column(name = "RADIOOPERATOR", length = 50)
	public String getRadioOperator() {
		return this.radioOperator;
	}

	public void setRadioOperator(String radioOperator) {
		this.radioOperator = radioOperator;
	}

	@Column(name = "ENGINEER", length = 50)
	public String getEngineer() {
		return this.engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	@Column(name = "PICORINSTRUCTORSIG", length = 50)
	public String getPicOrInstructorSig() {
		return this.picOrInstructorSig;
	}

	public void setPicOrInstructorSig(String picOrInstructorSig) {
		this.picOrInstructorSig = picOrInstructorSig;
	}

	@Column(name = "REMARKS", length = 300)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	@Column(name = "CREATOR", length = 36)
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TfQualPilotFlyrecordbook))
			return false;
		TfQualPilotFlyrecordbook castOther = (TfQualPilotFlyrecordbook) other;

		return ((this.getRecordId() == castOther.getRecordId()) || (this
				.getRecordId() != null && castOther.getRecordId() != null && this
				.getRecordId().equals(castOther.getRecordId())));
				
	}

	public int hashCode() {
		return this.recordId.hashCode();
	}

}