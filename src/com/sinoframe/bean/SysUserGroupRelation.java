package com.sinoframe.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUserGroupRealtion entity.
 * 用户用户组关系表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_GROUP_RELATION")
public class SysUserGroupRelation implements java.io.Serializable {

	// Fields
	private SysUserGroupRelationId id;
	private CmUser cmUser;
	private SysUserGroup sysUserGroup;
	private String state;

	// Constructors

	/** default constructor */
	public SysUserGroupRelation() {
	}


	public SysUserGroupRelation(SysUserGroupRelationId id, CmUser cmUser,
			SysUserGroup sysUserGroup, String state) {
		super();
		this.id = id;
		this.cmUser = cmUser;
		this.sysUserGroup = sysUserGroup;
		this.state = state;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public SysUserGroupRelationId getId() {
		return this.id;
	}

	public void setId(SysUserGroupRelationId id) {
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
	@JoinColumn(name = "GROUP_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysUserGroup getSysUserGroup() {
		return this.sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}