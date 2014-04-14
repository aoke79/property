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
 * SysOperateGroupRelation
 * @描述  表 "SYS_OPERATE_GROUP_RELATION" 的实体类
 * @作者 胡星
 * @版本 1.0
 */
@Entity
@Table(name = "SYS_OPERATE_GROUP_RELATION")
public class SysOperateGroupRelation implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4341381692942285073L;
	// Fields
	/** 操作组关系的联合ID */
	private SysOperateGroupRelationId id;
	/** 关联的操作组 */
	private SysOperateGroup sysOperateGroup;
	/** 关联的操作 */
	private SysOperate sysOperate;
	/** 操作组关系的状态（是否有效：1,0） */
	private String state;
	/** 备注 */
	private String opegrComment;

	// Constructors

	/** default constructor */
	public SysOperateGroupRelation() {
	}

	/** minimal constructor */
	public SysOperateGroupRelation(SysOperateGroupRelationId id,
			SysOperateGroup sysOperateGroup, SysOperate sysOperate) {
		this.id = id;
		this.sysOperateGroup = sysOperateGroup;
		this.sysOperate = sysOperate;
	}

	/** full constructor */
	public SysOperateGroupRelation(SysOperateGroupRelationId id,
			SysOperateGroup sysOperateGroup, SysOperate sysOperate,
			String state, String opegrComment) {
		this.id = id;
		this.sysOperateGroup = sysOperateGroup;
		this.sysOperate = sysOperate;
		this.state = state;
		this.opegrComment = opegrComment;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "operateGroupId", column = @Column(name = "OPERATE_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)),
			@AttributeOverride(name = "operateId", column = @Column(name = "OPERATE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public SysOperateGroupRelationId getId() {
		return this.id;
	}

	public void setId(SysOperateGroupRelationId id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_GROUP_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperateGroup getSysOperateGroup() {
		return this.sysOperateGroup;
	}

	public void setSysOperateGroup(SysOperateGroup sysOperateGroup) {
		this.sysOperateGroup = sysOperateGroup;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}

	@Column(name = "STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "OPEGR_COMMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getOpegrComment() {
		return this.opegrComment;
	}

	public void setOpegrComment(String opegrComment) {
		this.opegrComment = opegrComment;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}