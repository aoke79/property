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
 * SysRoleUsergroupRelation entity.
 * 角色用户组关系表
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ROLE_USERGROUP_RELATION")
public class SysRoleUserGroupRelation implements java.io.Serializable {

	// Fields

	private SysRoleUserGroupRelationId roleUsergroupId;
	private SysRole sysRole;
	private SysUserGroup sysUserGroup;
    private String state;
	
	// Constructors

	/** default constructor */
	public SysRoleUserGroupRelation() {
	}

	/** minimal constructor */
	public SysRoleUserGroupRelation(SysRoleUserGroupRelationId roleUsergroupId) {
		this.roleUsergroupId = roleUsergroupId;
	}

	/** full constructor */
	public SysRoleUserGroupRelation(SysRoleUserGroupRelationId roleUsergroupId,
			SysRole sysRole, SysUserGroup sysUserGroup) {
		this.roleUsergroupId = roleUsergroupId;
		this.sysRole = sysRole;
		this.sysUserGroup = sysUserGroup;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)),
	 })
	public SysRoleUserGroupRelationId getRoleUsergroupId() {
		return this.roleUsergroupId;
	}

	public void setRoleUsergroupId(SysRoleUserGroupRelationId roleUsergroupId) {
		this.roleUsergroupId = roleUsergroupId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", unique = false, nullable = true, insertable = false, updatable = false)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "GROUP_ID", unique = false, nullable = true, insertable = false, updatable = false)
	public SysUserGroup getSysUserGroup() {
		return this.sysUserGroup;
	}

	public void setSysUserGroup(SysUserGroup sysUserGroup) {
		this.sysUserGroup = sysUserGroup;
	}
    @Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
}