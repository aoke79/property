package com.sms.training.qualification.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sinoframe.bean.CmPeople;

/**
 * TfQualBasePilotInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_BASE_PILOT_INFO",  uniqueConstraints = {})
public class TfQualBasePilotInfo implements java.io.Serializable {

	// Fields

	private String infoid;
	private CmPeople cmPeople;
	private Long allflytime; //总飞行时间   已互换版
	private Long capflytime;
	private Long flytimeyear2;
	private Long flytimemonth12;
	private Long flytimemonth6;
	private Long allupdown;
	private Long updown;
	private Long updownmonth12;
	private Long updownday90;
	private Date medicalcertificate;
	private Date atpl;
	private String multiplecertificate;
	private Date dglastdate;
	private Date rlastdate;
	private Date gdlastdate;
	private String icao;
	private String prevlevelgrade;
	private Long dutytime;  //总飞行经历时间   已互换版

	// Constructors

	/** default constructor */
	public TfQualBasePilotInfo() {
	}

	/** minimal constructor */
	public TfQualBasePilotInfo(String infoid, CmPeople cmPeople) {
		this.infoid = infoid;
		this.cmPeople = cmPeople;
	}

	/** full constructor */
	public TfQualBasePilotInfo(String infoid, CmPeople cmPeople,
			Long allflytime, Long capflytime, Long flytimeyear2,
			Long flytimemonth12, Long flytimemonth6, Long allupdown,
			Long updown, Long updownmonth12, Long updownday90,
			Date medicalcertificate, Date atpl, String multiplecertificate,
			Date dglastdate, Date rlastdate, Date gdlastdate,String prevlevelgrade,Long dutytime) {
		this.infoid = infoid;
		this.cmPeople = cmPeople;
		this.allflytime = allflytime;
		this.capflytime = capflytime;
		this.flytimeyear2 = flytimeyear2;
		this.flytimemonth12 = flytimemonth12;
		this.flytimemonth6 = flytimemonth6;
		this.allupdown = allupdown;
		this.updown = updown;
		this.updownmonth12 = updownmonth12;
		this.updownday90 = updownday90;
		this.medicalcertificate = medicalcertificate;
		this.atpl = atpl;
		this.multiplecertificate = multiplecertificate;
		this.dglastdate = dglastdate;
		this.rlastdate = rlastdate;
		this.gdlastdate = gdlastdate;
		this.prevlevelgrade = prevlevelgrade;
		this.dutytime = dutytime;
	}

	// Property accessors
	@Id
	@Column(name = "INFOID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getInfoid() {
		return this.infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID", unique = false, nullable = false, insertable = true, updatable = true)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@Column(name = "ALLFLYTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getAllflytime() {
		return this.allflytime;
	}

	public void setAllflytime(Long allflytime) {
		this.allflytime = allflytime;
	}

	@Column(name = "CAPFLYTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getCapflytime() {
		return this.capflytime;
	}

	public void setCapflytime(Long capflytime) {
		this.capflytime = capflytime;
	}

	@Column(name = "FLYTIMEYEAR2", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getFlytimeyear2() {
		return this.flytimeyear2;
	}

	public void setFlytimeyear2(Long flytimeyear2) {
		this.flytimeyear2 = flytimeyear2;
	}

	@Column(name = "FLYTIMEMONTH12", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getFlytimemonth12() {
		return this.flytimemonth12;
	}

	public void setFlytimemonth12(Long flytimemonth12) {
		this.flytimemonth12 = flytimemonth12;
	}

	@Column(name = "FLYTIMEMONTH6", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getFlytimemonth6() {
		return this.flytimemonth6;
	}

	public void setFlytimemonth6(Long flytimemonth6) {
		this.flytimemonth6 = flytimemonth6;
	}

	@Column(name = "ALLUPDOWN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getAllupdown() {
		return this.allupdown;
	}

	public void setAllupdown(Long allupdown) {
		this.allupdown = allupdown;
	}

	@Column(name = "UPDOWN", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getUpdown() {
		return this.updown;
	}

	public void setUpdown(Long updown) {
		this.updown = updown;
	}

	@Column(name = "UPDOWNMONTH12", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getUpdownmonth12() {
		return this.updownmonth12;
	}

	public void setUpdownmonth12(Long updownmonth12) {
		this.updownmonth12 = updownmonth12;
	}

	@Column(name = "UPDOWNDAY90", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getUpdownday90() {
		return this.updownday90;
	}

	public void setUpdownday90(Long updownday90) {
		this.updownday90 = updownday90;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MEDICALCERTIFICATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getMedicalcertificate() {
		return this.medicalcertificate;
	}

	public void setMedicalcertificate(Date medicalcertificate) {
		this.medicalcertificate = medicalcertificate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ATPL", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAtpl() {
		return this.atpl;
	}

	public void setAtpl(Date atpl) {
		this.atpl = atpl;
	}

	@Column(name = "MULTIPLECERTIFICATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getMultiplecertificate() {
		return this.multiplecertificate;
	}

	public void setMultiplecertificate(String multiplecertificate) {
		this.multiplecertificate = multiplecertificate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DGLASTDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getDglastdate() {
		return this.dglastdate;
	}

	public void setDglastdate(Date dglastdate) {
		this.dglastdate = dglastdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RLASTDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getRlastdate() {
		return this.rlastdate;
	}

	public void setRlastdate(Date rlastdate) {
		this.rlastdate = rlastdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "GDLASTDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getGdlastdate() {
		return this.gdlastdate;
	}

	public void setGdlastdate(Date gdlastdate) {
		this.gdlastdate = gdlastdate;
	}

	@Column(name = "ICAO", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}
	@Column(name = "PREVLEVELGRADE", unique = false, nullable = true, insertable = true, updatable = true, length = 36)

	public String getPrevlevelgrade() {
		return prevlevelgrade;
	}

	public void setPrevlevelgrade(String prevlevelgrade) {
		this.prevlevelgrade = prevlevelgrade;
	}
	
	@Column(name = "DUTYTIME", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getDutytime() { 
		return this.dutytime;
	}

	public void setDutytime(Long dutytime) {
		this.dutytime = dutytime;
	}

	
	
}