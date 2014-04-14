package com.sms.training.qualification.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * AhPeruser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AH_PERUSER")
public class AhPeruser implements java.io.Serializable {

	// Fields

	private String peruuid;
	private AhSystem ahSystem;
	private String login;
	private String plaincode;
	private String password;
	private String name;
	private String idcard;
	private String unitname;
	private String deptname;
	private Set<AhMapping> ahMappings = new HashSet<AhMapping>(0);

	// Constructors

	/** default constructor */
	public AhPeruser() {
	}

	/** minimal constructor */
	public AhPeruser(String peruuid) {
		this.peruuid = peruuid;
	}

	/** full constructor */
	public AhPeruser(String peruuid, AhSystem ahSystem, String login,
			String plaincode, String password, String name, String idcard,
			String unitname, String deptname, Set<AhMapping> ahMappings) {
		this.peruuid = peruuid;
		this.ahSystem = ahSystem;
		this.login = login;
		this.plaincode = plaincode;
		this.password = password;
		this.name = name;
		this.idcard = idcard;
		this.unitname = unitname;
		this.deptname = deptname;
		this.ahMappings = ahMappings;
	}

	// Property accessors

	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "PERUUID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getPeruuid() {
		return this.peruuid;
	}

	public void setPeruuid(String peruuid) {
		this.peruuid = peruuid;
	}

	@ManyToOne(cascade={}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SYSID")
	public AhSystem getAhSystem() {
		return this.ahSystem;
	}

	public void setAhSystem(AhSystem ahSystem) {
		this.ahSystem = ahSystem;
	}

	@Column(name = "LOGIN",  length = 50)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "PLAINCODE",  length = 50)
	public String getPlaincode() {
		return this.plaincode;
	}

	public void setPlaincode(String plaincode) {
		this.plaincode = plaincode;
	}

	@Column(name = "PASSWORD",  length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME",  length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDCARD",  length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "UNITNAME",  length = 100)
	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	@Column(name = "DEPTNAME",  length = 100)
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "ahPeruser")
	public Set<AhMapping> getAhMappings() {
		return ahMappings;
	}

	public void setAhMappings(Set<AhMapping> ahMappings) {
		this.ahMappings = ahMappings;
	}

	

}