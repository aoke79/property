package com.sinoframe.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * SysOperateGroup
 * @描述  表 "SYS_OPERATE_GROUP" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "SYS_OPERATE_GROUP")
public class SysOperateGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6953441891611685332L;
	// Fields
	/** 操作组ID */
	private String id;
	/** 操作组名称 */
	private String name;
	/** 操作组状态：是否有效(1:有效;0:无效) */
	private String state;
	/** 操作组描述 */
	private String operateDesc;
	/** 备注 */
	private String opegComment;
	/** 操作组关联角色操作组关系的 Set 集合 */
	private Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations = new HashSet<SysRoleOperategroupRelation>(
			0);
	/** 操作组关联操作组关系的 Set 集合 */
	private Set<SysOperateGroupRelation> sysOperateGroupRelations = new HashSet<SysOperateGroupRelation>(
			0);

	// Constructors

	/** default constructor */
	public SysOperateGroup() {
	}

	/** minimal constructor */
	public SysOperateGroup(String id) {
		this.id = id;
	}

	/** full constructor */
	public SysOperateGroup(String id, String name, String state,
			String operateDesc, String opegComment,
			Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations,
			Set<SysOperateGroupRelation> sysOperateGroupRelations) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.operateDesc = operateDesc;
		this.opegComment = opegComment;
		this.sysRoleOperategroupRelations = sysRoleOperategroupRelations;
		this.sysOperateGroupRelations = sysOperateGroupRelations;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "OPERATE_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOperateDesc() {
		return this.operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	@Column(name = "OPEG_COMMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOpegComment() {
		return this.opegComment;
	}

	public void setOpegComment(String opegComment) {
		this.opegComment = opegComment;
	}

	@OneToMany(cascade = {}, fetch = FetchType.LAZY, mappedBy = "sysOperateGroup")
	public Set<SysRoleOperategroupRelation> getSysRoleOperategroupRelations() {
		return this.sysRoleOperategroupRelations;
	}

	public void setSysRoleOperategroupRelations(
			Set<SysRoleOperategroupRelation> sysRoleOperategroupRelations) {
		this.sysRoleOperategroupRelations = sysRoleOperategroupRelations;
	}

	@OneToMany(cascade = {}, fetch = FetchType.EAGER, mappedBy = "sysOperateGroup")
	public Set<SysOperateGroupRelation> getSysOperateGroupRelations() {
		return this.sysOperateGroupRelations;
	}

	public void setSysOperateGroupRelations(
			Set<SysOperateGroupRelation> sysOperateGroupRelations) {
		this.sysOperateGroupRelations = sysOperateGroupRelations;
	}

}