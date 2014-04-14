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
 * SysRoleOperategroupRelation
 * @描述  表 "SYS_ROLE_OPERATEGROUP_RELATION" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "SYS_ROLE_OPERATEGROUP_RELATION")
public class SysRoleOperategroupRelation implements java.io.Serializable,
		Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3191611645864498239L;
	// Fields
	/** 角色操作组关系的联合ID */
	private SysRoleOperategroupRelationId id;
	/** 关联的角色 */
	private SysRole sysRole;
	/** 关联的操作组 */
	private SysOperateGroup sysOperateGroup;
	/** 角色操作组关系的状态：是否有效（1,0） */
	private String state;
	/** 备注 */
	private String rorComment;

	// Constructors

	/** default constructor */
	public SysRoleOperategroupRelation() {
	}

	/** minimal constructor */
	public SysRoleOperategroupRelation(SysRoleOperategroupRelationId id,
			SysRole sysRole, SysOperateGroup sysOperateGroup) {
		this.id = id;
		this.sysRole = sysRole;
		this.sysOperateGroup = sysOperateGroup;
	}

	/** full constructor */
	public SysRoleOperategroupRelation(SysRoleOperategroupRelationId id,
			SysRole sysRole, SysOperateGroup sysOperateGroup, String state,
			String rorComment) {
		this.id = id;
		this.sysRole = sysRole;
		this.sysOperateGroup = sysOperateGroup;
		this.state = state;
		this.rorComment = rorComment;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)),
			@AttributeOverride(name = "operateGroupid", column = @Column(name = "OPERATE_GROUPID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public SysRoleOperategroupRelationId getId() {
		return this.id;
	}

	public void setId(SysRoleOperategroupRelationId id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_GROUPID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperateGroup getSysOperateGroup() {
		return this.sysOperateGroup;
	}

	public void setSysOperateGroup(SysOperateGroup sysOperateGroup) {
		this.sysOperateGroup = sysOperateGroup;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ROR_COMMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getRorComment() {
		return this.rorComment;
	}

	public void setRorComment(String rorComment) {
		this.rorComment = rorComment;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}