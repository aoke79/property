package com.sinoframe.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

 

/**
 * CmUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CM_USER")
public class CmUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	/** 用户编号 */
	private String userId;
	/** 所在国籍 */
	private SysCountry sysCountry;
	/** 登录帐号 */
	private String loginName;
	/** 所属职能机构编号 */
	private String funOrgId;
	/** 所属行政机构编号 */
	private String admOrgCode;
	/** 帐号密码 */
	private String accountPassword;
	/** 姓名 */
	private String name;
	/** 性别 1为男 0 为 女*/
	private String sex;
	/** 出生日期 */
	private Date birthday;
	/** 身份证号码 */
	private String identityCode;
	/** 电话 */
	private String phone;
	/** 用户邮箱 */
	private String email;
	/** 用户状态 1在职 0为离职  2则被逻辑删除*/
	private String state;
	/**  账户角色关系表*/
	private Set<SysRelationAccountRole> sysRelationAccountRoles = new HashSet<SysRelationAccountRole>(
			0);
	/** 机构用户关系表 */
	private Set<SysUserOrgRelation> sysUserOrgRelations = new HashSet<SysUserOrgRelation>(
			0);
	/** 操作组关系表 */
	private Set<CmUserOperateRelation> cmUserOperateRelations = new HashSet<CmUserOperateRelation>(
			0);
	/** 用户组关系表 */
	private Set<SysUserGroupRelation> sysUserGroupRelations = new HashSet<SysUserGroupRelation>(
			0);
	/** 消息历史记录表 */
	private Set<SysHistoryMessage> sysHistoryMessages = new HashSet<SysHistoryMessage>(
			0);
	/** 操作组关系表 */
	private Set<SysUserOperateInfo> sysUserOperateInfos = new HashSet<SysUserOperateInfo>(
			0);
	/** 用户菜单表 */
	private Set<SysUserCustomMenu> sysUserCustomMenus = new HashSet<SysUserCustomMenu>(
			0);
	/** 待处理消息提醒表 */
	private Set<SysBackLogMessage> sysBackLogMessages = new HashSet<SysBackLogMessage>(
			0);
	/** 修改密码历史记录表 */
	private Set<CmUserPasswordHistory> cmUserPasswordHistories = new HashSet<CmUserPasswordHistory>(
			0);
	/** 登录信息表 */
	private Set<SysLoginInfo> sysLoginInfos = new HashSet<SysLoginInfo>(0);
	/*//声明SecQaruser映射对象，并生成get、set方法
	private SecQaruser secQaruser;*/
//	//关联业务系统表
//	private Set<SecBaopesystem> businessSystemSet = new HashSet<SecBaopesystem>();
	//子系统传递标识
	private  String subsystemName;
	
	@Transient
	public String getSubsystemName() {
		return subsystemName;
	}

	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	//子系统标识
	private String subsystemId;
	
	//声明SecBaseChecksystem映射对象，并生成get、set方法
	//private SecBaseChecksystem secBaseChecksystem;

	// Constructors

	/** default constructor */
	public CmUser() {
	}

	/** minimal constructor */
	public CmUser(String userId, String loginName, String name) {
		this.userId = userId;
		this.loginName = loginName;
		this.name = name;
	}

	/** full constructor */
	public CmUser(String userId, SysCountry sysCountry, String loginName,
			String funOrgId, String admOrgCode, String accountPassword,
			String name, String sex, Date birthday, String identityCode,
			String phone, String email, String state,
			Set<SysRelationAccountRole> sysRelationAccountRoles,
			Set<SysUserOrgRelation> sysUserOrgRelations,
			Set<CmUserOperateRelation> cmUserOperateRelations,
			Set<SysUserGroupRelation> sysUserGroupRelations,
			Set<SysHistoryMessage> sysHistoryMessages,
			Set<SysUserOperateInfo> sysUserOperateInfos,
			Set<SysUserCustomMenu> sysUserCustomMenus,
			Set<SysBackLogMessage> sysBackLogMessages,
			Set<CmUserPasswordHistory> cmUserPasswordHistories,
			Set<SysLoginInfo> sysLoginInfos,String subsystemId) {
		this.userId = userId;
		this.sysCountry = sysCountry;
		this.loginName = loginName;
		this.funOrgId = funOrgId;
		this.admOrgCode = admOrgCode;
		this.accountPassword = accountPassword;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.identityCode = identityCode;
		this.phone = phone;
		this.email = email;
		this.state = state;
		this.sysRelationAccountRoles = sysRelationAccountRoles;
		this.sysUserOrgRelations = sysUserOrgRelations;
		this.cmUserOperateRelations = cmUserOperateRelations;
		this.sysUserGroupRelations = sysUserGroupRelations;
		this.sysHistoryMessages = sysHistoryMessages;
		this.sysUserOperateInfos = sysUserOperateInfos;
		this.sysUserCustomMenus = sysUserCustomMenus;
		this.sysBackLogMessages = sysBackLogMessages;
		this.cmUserPasswordHistories = cmUserPasswordHistories;
		this.sysLoginInfos = sysLoginInfos;
		this.subsystemId = subsystemId;
	}
    @Column(name="SUBSYSTEM_ID",length=36)
	public String getSubsystemId() {
		return subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	
	// Property accessors
	@Id
	@GeneratedValue(generator="userId-uuid")
	@GenericGenerator(name="userId-uuid", strategy = "uuid")
	@Column(name = "USER_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRYID", unique = false, nullable = true, insertable = true, updatable = true)
	public SysCountry getSysCountry() {
		return this.sysCountry;
	}

	public void setSysCountry(SysCountry sysCountry) {
		this.sysCountry = sysCountry;
	}

	@Column(name = "LOGIN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Transient
	public String getFunOrgId() {
		return this.funOrgId;
	}

	public void setFunOrgId(String funOrgId) {
		this.funOrgId = funOrgId;
	}

	@Transient
	public String getAdmOrgCode() {
		return this.admOrgCode;
	}

	public void setAdmOrgCode(String admOrgCode) {
		this.admOrgCode = admOrgCode;
	}

	@Column(name = "ACCOUNT_PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	@Column(name = "NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "IDENTITY_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getIdentityCode() {
		return this.identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	@Column(name = "PHONE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysRelationAccountRole> getSysRelationAccountRoles() {
		return this.sysRelationAccountRoles;
	}

	public void setSysRelationAccountRoles(
			Set<SysRelationAccountRole> sysRelationAccountRoles) {
		this.sysRelationAccountRoles = sysRelationAccountRoles;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysUserOrgRelation> getSysUserOrgRelations() {
		return this.sysUserOrgRelations;
	}

	public void setSysUserOrgRelations(
			Set<SysUserOrgRelation> sysUserOrgRelations) {
		this.sysUserOrgRelations = sysUserOrgRelations;
	}

	@OneToMany(cascade = {}, fetch = FetchType.EAGER, mappedBy = "cmUser")
	public Set<CmUserOperateRelation> getCmUserOperateRelations() {
		return this.cmUserOperateRelations;
	}

	public void setCmUserOperateRelations(
			Set<CmUserOperateRelation> cmUserOperateRelations) {
		this.cmUserOperateRelations = cmUserOperateRelations;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysUserGroupRelation> getSysUserGroupRelations() {
		return this.sysUserGroupRelations;
	}

	public void setSysUserGroupRelations(
			Set<SysUserGroupRelation> sysUserGroupRelations) {
		this.sysUserGroupRelations = sysUserGroupRelations;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysHistoryMessage> getSysHistoryMessages() {
		return this.sysHistoryMessages;
	}

	public void setSysHistoryMessages(Set<SysHistoryMessage> sysHistoryMessages) {
		this.sysHistoryMessages = sysHistoryMessages;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysUserOperateInfo> getSysUserOperateInfos() {
		return this.sysUserOperateInfos;
	}

	public void setSysUserOperateInfos(
			Set<SysUserOperateInfo> sysUserOperateInfos) {
		this.sysUserOperateInfos = sysUserOperateInfos;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysUserCustomMenu> getSysUserCustomMenus() {
		return this.sysUserCustomMenus;
	}

	public void setSysUserCustomMenus(Set<SysUserCustomMenu> sysUserCustomMenus) {
		this.sysUserCustomMenus = sysUserCustomMenus;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysBackLogMessage> getSysBackLogMessages() {
		return this.sysBackLogMessages;
	}

	public void setSysBackLogMessages(Set<SysBackLogMessage> sysBackLogMessages) {
		this.sysBackLogMessages = sysBackLogMessages;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<CmUserPasswordHistory> getCmUserPasswordHistories() {
		return this.cmUserPasswordHistories;
	}

	public void setCmUserPasswordHistories(
			Set<CmUserPasswordHistory> cmUserPasswordHistories) {
		this.cmUserPasswordHistories = cmUserPasswordHistories;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "cmUser")
	public Set<SysLoginInfo> getSysLoginInfos() {
		return this.sysLoginInfos;
	}

	public void setSysLoginInfos(Set<SysLoginInfo> sysLoginInfos) {
		this.sysLoginInfos = sysLoginInfos;
	}
	
	/*@OneToOne(mappedBy="cmUser",fetch=FetchType.LAZY)//定义映射对象来源(SecQaruser中定义的CmUser对象)
	public SecQaruser getSecQaruser() {
		return secQaruser;
	}

	public void setSecQaruser(SecQaruser secQaruser) {
		this.secQaruser = secQaruser;
	}*/
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="SEC_BASE_CHECKSYSTEM",
			joinColumns=@JoinColumn(name="USERID"),
			inverseJoinColumns=@JoinColumn(name="SYSTEMID"))
//	public Set<SecBaopesystem> getBusinessSystemSet() {
//		return businessSystemSet;
//	}
//
//	public void setBusinessSystemSet(Set<SecBaopesystem> businessSystemSet) {
//		this.businessSystemSet = businessSystemSet;
//	}

	/*@OneToOne(mappedBy="cmUser")//定义映射对象来源(SecBaseChecksystem中定义的CmUser对象)
	public SecBaseChecksystem getSecBaseChecksystem() {
		return secBaseChecksystem;
	}

	public void setSecBaseChecksystem(SecBaseChecksystem secBaseChecksystem) {
		this.secBaseChecksystem = secBaseChecksystem;
	}*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CmUser other = (CmUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}