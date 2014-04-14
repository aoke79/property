package com.sinoframe.bean;

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
 * CmUserPasswordHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CM_USER_PASSWORD_HISTORY")
public class CmUserPasswordHistory implements java.io.Serializable {

	// Fields
	/** 历史密码id*/
	private String id;
	/** 用户*/
	private CmUser cmUser;
	/** 所改的密码*/
	private String password;
	/**更改时间*/
	private Date usedate;

	// Constructors

	/** default constructor */
	public CmUserPasswordHistory() {
	}

	/** minimal constructor */
	public CmUserPasswordHistory(String id) {
		this.id = id;
	}

	/** full constructor */
	public CmUserPasswordHistory(String id, CmUser cmUser, String password,
			Date usedate) {
		this.id = id;
		this.cmUser = cmUser;
		this.password = password;
		this.usedate = usedate;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="userPasswordHistory-uuid")
	@GenericGenerator(name="userPasswordHistory-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@Column(name = "PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USEDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getUsedate() {
		return this.usedate;
	}

	public void setUsedate(Date usedate) {
		this.usedate = usedate;
	}

}