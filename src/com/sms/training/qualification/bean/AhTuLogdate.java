package com.sms.training.qualification.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "AH_TU_LOGDATE")
public class AhTuLogdate implements java.io.Serializable{
	
	private String loguuid;
	private String useruuid;
	private String optime;
	private String opname;
	private String micip;
	private String username;
	private String sysname;
	
	public AhTuLogdate() {
	}

	/** minimal constructor */
	public AhTuLogdate(String loguuid) {
		this.loguuid = loguuid;
	}
	
	public AhTuLogdate(String loguuid, String useruuid, String username,
			String sysname, String optime, String opname, String micip) {
		this.loguuid = loguuid;
		this.optime = optime;
		this.opname = opname;
		this.micip = micip;
		this.username = username;
		this.sysname = sysname;
	}
	
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "LOGUUID", unique = true, nullable = false, length = 36)
	public String getLoguuid() {
		return loguuid;
	}
	public void setLoguuid(String loguuid) {
		this.loguuid = loguuid;
	}
	
	@Column(name = "USERUUID", length = 36)
	public String getUseruuid() {
		return useruuid;
	}
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}
	
	@Column(name = "OPTIME", length = 36)
	public String getOptime() {
		return optime;
	}
	public void setOptime(String optime) {
		this.optime = optime;
	}
	
	@Column(name = "OPNAME", length = 500)
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	
	@Column(name = "MICIP", length = 36)
	public String getMicip() {
		return micip;
	}
	public void setMicip(String micip) {
		this.micip = micip;
	}
	
	@Column(name = "USERNAME", length = 36)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "SYSNAME", length = 36)
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	
	
	
}
