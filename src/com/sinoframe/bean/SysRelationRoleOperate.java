package com.sinoframe.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;






/**
 * SysRelationRoleOperate entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_RELATION_ROLE_OPERATE")
public class SysRelationRoleOperate implements java.io.Serializable {

	// Fields

	private SysRelationRoleOperateId id;
	private SysRole sysRole;
	private SysOperate sysOperate;
	private String description;
	private String flag;

	// Constructors

	/** default constructor */
	public SysRelationRoleOperate() {
	}
	
	/** minimal constructor */
	public SysRelationRoleOperate(SysRelationRoleOperateId id, SysRole sysRole,
			SysOperate sysOperate) {
		this.id = id;
		this.sysRole = sysRole;
		this.sysOperate = sysOperate;
	}

	/** full constructor */
	public SysRelationRoleOperate(SysRelationRoleOperateId id, SysRole sysRole,
			SysOperate sysOperate, String description, String flag) {
		this.id = id;
		this.sysRole = sysRole;
		this.sysOperate = sysOperate;
		this.description = description;
		this.flag = flag;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)),
			@AttributeOverride(name = "operateId", column = @Column(name = "OPERATE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)) })
	public SysRelationRoleOperateId getId() {
		return this.id;
	}

	public void setId(SysRelationRoleOperateId id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = { }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}

	@Column(name = "DESCRIPTION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append("----------id.getOperateId()------------");
		sBuffer.append(id.getOperateId()+"---------------------");
		sBuffer.append("----------id.getOperateId()------------");
		sBuffer.append(getSysOperate().getOperateName()+"---------------------");
		sBuffer.append("----------id.getRoleId()------------");
		sBuffer.append(id.getRoleId()+"---------------------");
		sBuffer.append(getSysRole().getRoleName()+"---------------------");
		sBuffer.append("----------description------------");
		sBuffer.append(description+"---------------------");
		sBuffer.append("----------flag------------");
		sBuffer.append(flag+"---------------------");
		return sBuffer.toString();
	}

}