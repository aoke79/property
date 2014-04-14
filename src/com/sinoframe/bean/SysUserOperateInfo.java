package com.sinoframe.bean;

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
 * SysUserOperateInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_OPERATE_INFO")
public class SysUserOperateInfo implements java.io.Serializable {

	// Fields

	private String id;
	private CmUser cmUser;
	private SysOperate sysOperate;
	private String operateDate;
	private String describe;
	private String userid;
	private String operateid;

	// Constructors

	/** default constructor */
	public SysUserOperateInfo() {
	}

	/** minimal constructor */
	public SysUserOperateInfo(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysUserOperateInfo(String id, CmUser cmUser, SysOperate sysOperate,
			String operateDate, String describe) {
		this.id = id;
		this.cmUser = cmUser;
		this.sysOperate = sysOperate;
		this.operateDate = operateDate;
		this.describe = describe;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="sysUserOperateInfo-uuid")
	@GenericGenerator(name="sysUserOperateInfo-uuid", strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public CmUser getCmUser() {
		return this.cmUser;
	}

	public void setCmUser(CmUser cmUser) {
		this.cmUser = cmUser;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}

	@Column(name = "OPERATE_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	@Column(name = "DESCRIBE", unique = false, nullable = true, insertable = true, updatable = true)
	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	@Column(name = "USER_ID")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "OPERATE_ID" )
	public String getOperateid() {
		return operateid;
	}

	public void setOperateid(String operateid) {
		this.operateid = operateid;
	}
	

}