package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysRoleOperategroupRelationId
 * @描述  表 "SYS_ROLE_OPERATEGROUP_RELATION" 的 ID 主键类
 * @作者 胡星
 * @版本 1.0
 */
@Embeddable
public class SysRoleOperategroupRelationId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2000906320911021715L;
	// Fields
	/** 角色ID */
	private String roleId;
	/** 操作组ID */
 	private String operateGroupid;

	// Constructors

	/** default constructor */
	public SysRoleOperategroupRelationId() {
	}

	/** full constructor */
	public SysRoleOperategroupRelationId(String roleId, String operateGroupid) {
		this.roleId = roleId;
		this.operateGroupid = operateGroupid;
	}

	// Property accessors

	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "OPERATE_GROUPID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getOperateGroupid() {
		return this.operateGroupid;
	}

	public void setOperateGroupid(String operateGroupid) {
		this.operateGroupid = operateGroupid;
	}

}