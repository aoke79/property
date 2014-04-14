package com.sms.training.qualification.bean;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseAirPlanType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BASE_AIRPLANTYPE")
public class BaseAirPlanType implements java.io.Serializable {

	// Fields
	private String id;
	private String atrid;
	private String atrdesc;
	private String atrkind;
	private String status;

	// Constructors

	/** default constructor */
	public BaseAirPlanType() {
	}

	/** minimal constructor */
	public BaseAirPlanType(String id) {
		this.id = id;
	}

	/** full constructor */
	public BaseAirPlanType(String id, String atrid, String atrdesc,
			String atrkind,String status) {
		this.id = id;
		this.atrid = atrid;
		this.atrdesc = atrdesc;
		this.atrkind = atrkind;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ATRID", length = 36)
	public String getAtrid() {
		return this.atrid;
	}

	public void setAtrid(String atrid) {
		this.atrid = atrid;
	}

	@Column(name = "ATRDESC", length = 300)
	public String getAtrdesc() {
		return this.atrdesc;
	}

	public void setAtrdesc(String atrdesc) {
		this.atrdesc = atrdesc;
	}

	@Column(name = "ATRKIND", length = 1)
	public String getAtrkind() {
		return this.atrkind;
	}

	public void setAtrkind(String atrkind) {
		this.atrkind = atrkind;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}