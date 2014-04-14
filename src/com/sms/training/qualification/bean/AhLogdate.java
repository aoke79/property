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
 * AhLogdate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_LOGDATE")
public class AhLogdate implements java.io.Serializable {

	// Fields

	private String loguuid;
	private AhUser anUser;
	private AhSystem ahSystem;
	private Date optime;
	private String opname;
	private String micip;
	private String username;
	private String sysname;

	// Constructors

	/** default constructor */
	public AhLogdate() {
	}

	/** minimal constructor */
	public AhLogdate(String loguuid) {
		this.loguuid = loguuid;
	}

	/** full constructor 
	 * @param anUser 
	 * @param ahSystem */
	public AhLogdate(String loguuid, String useruuid, String username,
			String name, String unitname, String deptname, String sysid,
			String sysname, Date optime, String opname, String micip, AhUser anUser, AhSystem ahSystem) {
		this.loguuid = loguuid;
		this.anUser = anUser;
		this.ahSystem = ahSystem;
		this.optime = optime;
		this.opname = opname;
		this.micip = micip;
		this.username = username;
		this.sysname = sysname;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "LOGUUID", unique = true, nullable = false, length = 36)
	public String getLoguuid() {
		return this.loguuid;
	}

	public void setLoguuid(String loguuid) {
		this.loguuid = loguuid;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPTIME", length = 7)
	public Date getOptime() {
		return this.optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}
	
	@Column(name = "OPNAME", length = 100)
	public String getOpname() {
		return this.opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}
	
	@Column(name = "MICIP", length = 36)
	public String getMicip() {
		return this.micip;
	}

	public void setMicip(String micip) {
		this.micip = micip;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USERUUID")
	public AhUser getAnUser() {
		return anUser;
	}

	public void setAnUser(AhUser anUser) {
		this.anUser = anUser;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSID")
	public AhSystem getAhSystem() {
		return ahSystem;
	}

	public void setAhSystem(AhSystem ahSystem) {
		this.ahSystem = ahSystem;
	}
	
	@Column(name = "USERNAME", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "SYSNAME", length = 10)
	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

}