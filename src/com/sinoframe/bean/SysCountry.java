package com.sinoframe.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysCountry entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_COUNTRY")
public class SysCountry implements java.io.Serializable {

	// Fields
	/** 国家ID*/
	private String id;
	/** 国家名称 */
	private String name;
	/** 用户表*/
	private Set<CmUser> tbUsers = new HashSet<CmUser>(0);

	// Constructors

	/** default constructor */
	public SysCountry() {
	}

	/** minimal constructor */
	public SysCountry(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public SysCountry(String id, String name, Set<CmUser> tbUsers) {
		this.id = id;
		this.name = name;
		this.tbUsers = tbUsers;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="sys-country-uuid")
	@GenericGenerator(name="sys-country-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sysCountry")
	public Set<CmUser> getTbUsers() {
		return this.tbUsers;
	}

	public void setTbUsers(Set<CmUser> tbUsers) {
		this.tbUsers = tbUsers;
	}

}