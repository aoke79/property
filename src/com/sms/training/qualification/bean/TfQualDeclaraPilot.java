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
 * TfQualDeclaraPilot entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TF_QUAL_DECLARA_PILOT", uniqueConstraints = {})
public class TfQualDeclaraPilot implements java.io.Serializable {

	// Fields

	private String detailsid;
	private TfQualDeclarInfo tfQualDeclarInfo;

	private CmPeople cmPeople;
//	private String typeid;
	private TfQualBaseType tfQualBaseType;
	
	private String orgRole;
	 
	private String state;
	private String groupid;
	private String subProcessid;
	//后加三个字段 用于重复报表删除操作
	private String tranplanid;
	private String appreportid;
	private String appnoticeid;

 
	@Column(name = "GROUPID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Column(name = "ORG_ROLE", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	public String getOrgRole() {
		return orgRole;
	}

	public void setOrgRole(String orgRole) {
		this.orgRole = orgRole;
	}
	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getState() {
		return state;
	}

	

	public void setState(String state) {
		this.state = state;
	}

	// Constructors
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPEID", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualBaseType getTfQualBaseType() {
		return tfQualBaseType;
	}

	public void setTfQualBaseType(TfQualBaseType tfQualBaseType) {
		this.tfQualBaseType = tfQualBaseType;
	}

	/** default constructor */
	public TfQualDeclaraPilot() {
	}

	/** minimal constructor */
	public TfQualDeclaraPilot(String detailsid) {
		this.detailsid = detailsid;
	}

	/** full constructor */
	public TfQualDeclaraPilot(String detailsid,
			TfQualDeclarInfo tfQualDeclarInfo, CmPeople cmPeople, String typeid) {
		this.detailsid = detailsid;
		this.tfQualDeclarInfo = tfQualDeclarInfo;
		this.cmPeople = cmPeople;
		this.tfQualBaseType = tfQualBaseType;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "DETAILSID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getDetailsid() {
		return this.detailsid;
	}

	public void setDetailsid(String detailsid) {
		this.detailsid = detailsid;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "DECLAREDINFOID", unique = false, nullable = true, insertable = true, updatable = true)
	public TfQualDeclarInfo getTfQualDeclarInfo() {
		return this.tfQualDeclarInfo;
	}

	public void setTfQualDeclarInfo(TfQualDeclarInfo tfQualDeclarInfo) {
		this.tfQualDeclarInfo = tfQualDeclarInfo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PILOTID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmPeople getCmPeople() {
		return this.cmPeople;
	}

	public void setCmPeople(CmPeople cmPeople) {
		this.cmPeople = cmPeople;
	}

//	@Column(name = "TYPEID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
//	public String getTypeid() {
//		return this.typeid;
//	}
//
//	public void setTypeid(String typeid) {
//		this.typeid = typeid;
//	}
	@Column(name = "SUB_PROCESSID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getSubProcessid() {
		return subProcessid;
	}

	public void setSubProcessid(String subProcessid) {
		this.subProcessid = subProcessid;
	}
	@Column(name = "TRANPLANID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getTranplanid() {
		return tranplanid;
	}

	public void setTranplanid(String tranplanid) {
		this.tranplanid = tranplanid;
	}
	@Column(name = "APPREPORTID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getAppreportid() {
		return appreportid;
	}
	
	public void setAppreportid(String appreportid) {
		this.appreportid = appreportid;
	}
	@Column(name = "APPNOTICEID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getAppnoticeid() {
		return appnoticeid;
	}
	
	public void setAppnoticeid(String appnoticeid) {
		this.appnoticeid = appnoticeid;
	}
	
	
	
}