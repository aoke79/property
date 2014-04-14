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

import com.sinoframe.bean.CmPeople;
import com.sms.security.basicdata.bean.BaseAirplanType;
/**
 * TfQualPilotTechrecord entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_TECHRECORD",  uniqueConstraints = {})
public class TfQualPilotTechrecord implements java.io.Serializable {

	// Fields

	private String ptrseq;
	private TfQualPilotLicence tfQualPilotLicence;
	private BaseAirPlanType baseAirplantype;
	private CmPeople cmPeople;
	private String ptgradeid;
	private Date ptrapproved;
	private Date ptrexpired;
	private String ptrils;
	private String ptrcurrent;
	private Long ptrfltexptimetotaltotal;
	private Long ptrmovemnettotal;

	// Constructors

	/** default constructor */
	public TfQualPilotTechrecord() {
	}

	/** minimal constructor */
	public TfQualPilotTechrecord(String ptrseq,
			TfQualPilotLicence tfQualPilotLicence,
			BaseAirPlanType baseAirplantype, CmPeople cmPeople, String ptgradeid) {
		this.ptrseq = ptrseq;
		this.tfQualPilotLicence = tfQualPilotLicence;
		this.baseAirplantype = baseAirplantype;
		this.cmPeople = cmPeople;
		this.ptgradeid = ptgradeid;
	}

	/** full constructor */
	public TfQualPilotTechrecord(String ptrseq,
			TfQualPilotLicence tfQualPilotLicence,
			BaseAirPlanType baseAirplantype, CmPeople cmPeople, String ptgradeid,
			Date ptrapproved, Date ptrexpired, String ptrils,
			String ptrcurrent, Long ptrfltexptimetotaltotal,
			Long ptrmovemnettotal) {
		this.ptrseq = ptrseq;
		this.tfQualPilotLicence = tfQualPilotLicence;
		this.baseAirplantype = baseAirplantype;
		this.cmPeople = cmPeople;
		this.ptgradeid = ptgradeid;
		this.ptrapproved = ptrapproved;
		this.ptrexpired = ptrexpired;
		this.ptrils = ptrils;
		this.ptrcurrent = ptrcurrent;
		this.ptrfltexptimetotaltotal = ptrfltexptimetotaltotal;
		this.ptrmovemnettotal = ptrmovemnettotal;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PTRSEQ", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getPtrseq() {
		return this.ptrseq;
	}

	public void setPtrseq(String ptrseq) {
		this.ptrseq = ptrseq;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLCSEQ", unique = false, nullable = false, insertable = true, updatable = true)
	public TfQualPilotLicence getTfQualPilotLicence() {
		return this.tfQualPilotLicence;
	}

	public void setTfQualPilotLicence(TfQualPilotLicence tfQualPilotLicence) {
		this.tfQualPilotLicence = tfQualPilotLicence;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ATRID", unique = false, nullable = false, insertable = true, updatable = true)
	public BaseAirPlanType getBaseAirplantype() {
		return this.baseAirplantype;
	}

	public void setBaseAirplantype(BaseAirPlanType baseAirplantype) {
		this.baseAirplantype = baseAirplantype;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID", unique = false, nullable = false, insertable = true, updatable = true)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@Column(name = "PTGRADEID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getPtgradeid() {
		return this.ptgradeid;
	}

	public void setPtgradeid(String ptgradeid) {
		this.ptgradeid = ptgradeid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PTRAPPROVED", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPtrapproved() {
		return this.ptrapproved;
	}

	public void setPtrapproved(Date ptrapproved) {
		this.ptrapproved = ptrapproved;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PTREXPIRED", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPtrexpired() {
		return this.ptrexpired;
	}

	public void setPtrexpired(Date ptrexpired) {
		this.ptrexpired = ptrexpired;
	}

	@Column(name = "PTRILS", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getPtrils() {
		return this.ptrils;
	}

	public void setPtrils(String ptrils) {
		this.ptrils = ptrils;
	}

	@Column(name = "PTRCURRENT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getPtrcurrent() {
		return this.ptrcurrent;
	}

	public void setPtrcurrent(String ptrcurrent) {
		this.ptrcurrent = ptrcurrent;
	}

	@Column(name = "PTRFLTEXPTIMETOTALTOTAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPtrfltexptimetotaltotal() {
		return this.ptrfltexptimetotaltotal;
	}

	public void setPtrfltexptimetotaltotal(Long ptrfltexptimetotaltotal) {
		this.ptrfltexptimetotaltotal = ptrfltexptimetotaltotal;
	}

	@Column(name = "PTRMOVEMNETTOTAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPtrmovemnettotal() {
		return this.ptrmovemnettotal;
	}

	public void setPtrmovemnettotal(Long ptrmovemnettotal) {
		this.ptrmovemnettotal = ptrmovemnettotal;
	}

}