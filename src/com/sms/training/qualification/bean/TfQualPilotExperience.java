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
 * 飞行员工作简历  entity. 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_PILOT_EXPERIENCE")
public class TfQualPilotExperience implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	/** ID*/
	private String experienceid;
	/** 飞行员*/
	private CmPeople cmPeople;
	/** 开始时间*/
	private String startdt;
	/** 结束时间*/
	private String enddt;
	/** 地点*/
	private String locations;
	/** 部门*/
	private String orgid;
	/** 职务*/
	private String title;

	// Constructors

	/** default constructor */
	public TfQualPilotExperience() {
	}

	/** full constructor */
	public TfQualPilotExperience(CmPeople cmPeople, String startdt,
			String enddt, String locations, String orgid, String title) {
		this.cmPeople = cmPeople;
		this.startdt = startdt;
		this.enddt = enddt;
		this.locations = locations;
		this.orgid = orgid;
		this.title = title;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXPERIENCEID", unique = true, nullable = false, length = 36)
	public String getExperienceid() {
		return this.experienceid;
	}

	public void setExperienceid(String experienceid) {
		this.experienceid = experienceid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID")
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

	@Column(name = "STARTDT", length = 10)
	public String getStartdt() {
		return this.startdt;
	}

	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	@Column(name = "ENDDT", length = 10)
	public String getEnddt() {
		return this.enddt;
	}

	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	@Column(name = "LOCATIONS", length = 150)
	public String getLocations() {
		return this.locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	@Column(name = "ORGID", length = 60)
	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Column(name = "TITLE", length = 60)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}