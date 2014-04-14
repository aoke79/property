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

/**
 * AhMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_MAPPING")
public class AhMapping implements java.io.Serializable {

	// Fields

	private String uuid;
	private AhSystem ahSystem;
	private AhPeruser ahPeruser;
	private AhUser ahUser;
	private String mflag;

	// Constructors

	/** default constructor */
	public AhMapping() {
	}

	/** minimal constructor */
	public AhMapping(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public AhMapping(String uuid, AhSystem ahSystem, AhPeruser ahPeruser,
			AhUser ahUser, String mflag) {
		this.uuid = uuid;
		this.ahSystem = ahSystem;
		this.ahPeruser = ahPeruser;
		this.ahUser = ahUser;
		this.mflag = mflag;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "UUID", unique = true, nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SYSID", unique = false, nullable = true)
	public AhSystem getAhSystem() {
		return this.ahSystem;
	}

	public void setAhSystem(AhSystem ahSystem) {
		this.ahSystem = ahSystem;
	}

	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "PERUUID", unique = false, nullable = true)
	public AhPeruser getAhPeruser() {
		return this.ahPeruser;
	}

	public void setAhPeruser(AhPeruser ahPeruser) {
		this.ahPeruser = ahPeruser;
	}

	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "USERUUID", unique = false, nullable = true)
	public AhUser getAhUser() {
		return this.ahUser;
	}

	public void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}

	@Column(name = "MFLAG", length = 1)
	public String getMflag() {
		return this.mflag;
	}

	public void setMflag(String mflag) {
		this.mflag = mflag;
	}

}