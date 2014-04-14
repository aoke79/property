package com.sms.training.qualification.bean;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CM_USER")
public class QuaCmUser implements java.io.Serializable {

	// Fields
	private String userId;	
	private String loginName;
	private String funOrgId;
	private String admOrgCode;
	private String accountPassword;
	private String name;
	private String sex;
	private Date birthday;
	private String identityCode;
	private String phone;
	private String email;
	private String state;
	private String countryid;
	// Constructors

	/** default constructor */
	public QuaCmUser() {
	}

	/** minimal constructor */
	public QuaCmUser(String userId, String loginName, String name) {
		this.userId = userId;
		this.loginName = loginName;
		this.name = name;
	}

	/** full constructor */
	
	// Property accessors
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LOGIN_NAME", nullable = false, length = 128)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "FUN_ORG_ID", length = 36)
	public String getFunOrgId() {
		return this.funOrgId;
	}

	public void setFunOrgId(String funOrgId) {
		this.funOrgId = funOrgId;
	}

	@Column(name = "ADM_ORG_CODE", length = 36)
	public String getAdmOrgCode() {
		return this.admOrgCode;
	}

	public void setAdmOrgCode(String admOrgCode) {
		this.admOrgCode = admOrgCode;
	}

	@Column(name = "ACCOUNT_PASSWORD", length = 50)
	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	@Column(name = "NAME", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "IDENTITY_CODE", length = 20)
	public String getIdentityCode() {
		return this.identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATE", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "COUNTRYID", length = 1)
	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	

}