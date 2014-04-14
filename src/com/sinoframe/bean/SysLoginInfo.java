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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * SysLoginInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_LOGIN_INFO")
public class SysLoginInfo implements java.io.Serializable {

	// Fields

	/** 登录id*/
	private String id;
	/** 用户*/
	private CmUser cmUser;
	/** 首次登录日期*/
	private Date loginDate;
	/** 最后一次登录日期*/
	private Date lastLogin;
	/** 登录成功次数*/
	private Long loginCount;
	/** 登录失败次数*/
	private Long loginFailCount;
	/** 在线状态 1为在线 0为离线*/
	private String onlineState;
	/** 所存session id*/
	private String sessionId;
	/** 当前ip地址*/
	private String ip;

	// Constructors

	public SysLoginInfo(CmUser cmUser, String ip,String onlineState) {
		super();
		this.cmUser = cmUser;
		this.ip = ip;
		this.onlineState = onlineState;
	}

	/** default constructor */
	public SysLoginInfo() {
	}
	
	/** minimal constructor */
	public SysLoginInfo(String id, CmUser cmUser, Date loginDate,
			String onlineState) {
		this.id = id;
		this.cmUser = cmUser;
		this.loginDate = loginDate;
		this.onlineState = onlineState;
	}

	/** full constructor */
	public SysLoginInfo(String id, CmUser cmUser, Date loginDate,
			Date lastLogin, Long loginCount, Long loginFailCount,
			String onlineState, String sessionId, String ip) {
		this.id = id;
		this.cmUser = cmUser;
		this.loginDate = loginDate;
		this.lastLogin = lastLogin;
		this.loginCount = loginCount;
		this.loginFailCount = loginFailCount;
		this.onlineState = onlineState;
		this.sessionId = sessionId;
		this.ip = ip;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="sys-login-info-uuid")
	@GenericGenerator(name="sys-login-info-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOGIN_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "LOGIN_COUNT", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	@Column(name = "LOGIN_FAIL_COUNT", unique = false, nullable = true, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getLoginFailCount() {
		return this.loginFailCount;
	}

	public void setLoginFailCount(Long loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	@Column(name = "ONLINE_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getOnlineState() {
		return this.onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	@Column(name = "SESSION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "IP", unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}