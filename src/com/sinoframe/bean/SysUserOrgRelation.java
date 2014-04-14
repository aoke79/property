package com.sinoframe.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SysUserOrgRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_USER_ORG_RELATION")
public class SysUserOrgRelation implements java.io.Serializable {

	// Fields

	private SysUserOrgRelationId id;
	private CmUser cmUser;
	private SysOrganization sysOrganization;

	// Constructors

	/** default constructor */
	public SysUserOrgRelation() {
		
	}

	/** user org constructor */
	public SysUserOrgRelation( CmUser cmUser,
			SysOrganization sysOrganization) {
		this.cmUser = cmUser;
		this.sysOrganization = sysOrganization;
	}

	/** full constructor */
	public SysUserOrgRelation(SysUserOrgRelationId id, CmUser cmUser,
			SysOrganization sysOrganization) {
		this.id = id;
		this.cmUser = cmUser;
		this.sysOrganization = sysOrganization;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)),
			@AttributeOverride(name = "orgId", column = @Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public SysUserOrgRelationId getId() {
		return this.id;
	}

	public void setId(SysUserOrgRelationId id) {
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

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "ORG_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

}