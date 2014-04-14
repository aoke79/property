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
 * AhAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_ADMIN")
public class AhAdmin implements java.io.Serializable {

	// Fields

	private String uuid;
	private AhSystem ahSystem;
	private AhUser ahUser;

	// Constructors

	/** default constructor */
	public AhAdmin() {
	}

	/** minimal constructor */
	public AhAdmin(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public AhAdmin(String uuid, AhSystem ahSystem, AhUser ahUser) {
		this.uuid = uuid;
		this.ahSystem = ahSystem;
		this.ahUser = ahUser;
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
	@JoinColumn(name = "USERUUID", unique = false, nullable = true)
	public AhUser getAhUser() {
		return this.ahUser;
	}

	public void setAhUser(AhUser ahUser) {
		this.ahUser = ahUser;
	}

}