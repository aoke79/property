package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysRelationRoleOperateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysRelationRoleOperateId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String operateId;

	// Constructors

	/** default constructor */
	public SysRelationRoleOperateId() {
	}

	/** full constructor */
	public SysRelationRoleOperateId(String roleId, String operateId) {
		this.roleId = roleId;
		this.operateId = operateId;
	}

	// Property accessors

	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "OPERATE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}


}