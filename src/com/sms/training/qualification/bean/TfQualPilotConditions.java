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

import com.sinoframe.bean.CmPeople;

/**
 * TfQualPilotConditions entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_CONDITIONS", uniqueConstraints = {})
public class TfQualPilotConditions implements java.io.Serializable {

	// Fields

	private String conditionsid;
	private TfQualBaseConditions tfQualBaseConditions;
	private CmPeople cmPeople;
	private String status;

	// Constructors

	/** default constructor */
	public TfQualPilotConditions() {
	}

	/** minimal constructor */
	public TfQualPilotConditions(String conditionsid,
			TfQualBaseConditions tfQualBaseConditions, CmPeople cmPeople) {
		this.conditionsid = conditionsid;
		this.tfQualBaseConditions = tfQualBaseConditions;
		this.cmPeople = cmPeople;
	}

	/** full constructor */
	public TfQualPilotConditions(String conditionsid,
			TfQualBaseConditions tfQualBaseConditions, CmPeople cmPeople,
			String status) {
		this.conditionsid = conditionsid;
		this.tfQualBaseConditions = tfQualBaseConditions;
		this.cmPeople = cmPeople;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "CONDITIONSID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getConditionsid() {
		return this.conditionsid;
	}

	public void setConditionsid(String conditionsid) {
		this.conditionsid = conditionsid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONDITIONID", unique = false, nullable = false, insertable = true, updatable = true)
	public TfQualBaseConditions getTfQualBaseConditions() {
		return this.tfQualBaseConditions;
	}

	public void setTfQualBaseConditions(
			TfQualBaseConditions tfQualBaseConditions) {
		this.tfQualBaseConditions = tfQualBaseConditions;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID", unique = false, nullable = false, insertable = true, updatable = true)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}