package com.sms.training.qualification.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * AhSystem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_SYSTEM")
public class AhSystem implements java.io.Serializable {

	// Fields

	private String sysid;
	private String sysname;
	private String sysurl;
//	private Set<AhMapping> ahMappings = new HashSet<AhMapping>(0);
//	private Set<AhPeruser> ahPerusers = new HashSet<AhPeruser>(0);

	// Constructors

	/** default constructor */
	public AhSystem() {
	}

	/** minimal constructor */
	public AhSystem(String sysid) {
		this.sysid = sysid;
	}

	/** full constructor */
	public AhSystem(String sysid, String sysname, String sysurl,
			Set<AhMapping> ahMappings, Set<AhPeruser> ahPerusers) {
		this.sysid = sysid;
		this.sysname = sysname;
		this.sysurl = sysurl;
//		this.ahMappings = ahMappings;
//		this.ahPerusers = ahPerusers;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "SYSID", unique = true, nullable = false, length = 2)
	public String getSysid() {
		return this.sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	@Column(name = "SYSNAME", length = 10)
	public String getSysname() {
		return this.sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	@Column(name = "SYSURL", length = 100)
	public String getSysurl() {
		return this.sysurl;
	}

	public void setSysurl(String sysurl) {
		this.sysurl = sysurl;
	}

//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "ahSystem")
//	public Set<AhMapping> getAhMappings() {
//		return ahMappings;
//	}
//
//	public void setAhMappings(Set<AhMapping> ahMappings) {
//		this.ahMappings = ahMappings;
//	}
//
//	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "ahPeruser")
//	public Set<AhPeruser> getAhPerusers() {
//		return ahPerusers;
//	}
//
//	public void setAhPerusers(Set<AhPeruser> ahPerusers) {
//		this.ahPerusers = ahPerusers;
//	}

	

}