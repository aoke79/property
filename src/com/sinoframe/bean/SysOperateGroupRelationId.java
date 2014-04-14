package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysOperateGroupRelationId
 * @描述  表 "SYS_OPERATE_GROUP_RELATION" 的 ID 主键类
 * @作者 胡星
 * @版本 1.0
 */
@Embeddable
public class SysOperateGroupRelationId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3407149120476581814L;
	// Fields
	/** 操作组ID */
	private String operateGroupId;
	/** 操作ID */
	private String operateId;

	// Constructors

	/** default constructor */
	public SysOperateGroupRelationId() {
	}

	/** full constructor */
	public SysOperateGroupRelationId(String operateGroupId, String operateId) {
		this.operateGroupId = operateGroupId;
		this.operateId = operateId;
	}

	// Property accessors

	@Column(name = "OPERATE_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getOperateGroupId() {
		return this.operateGroupId;
	}

	public void setOperateGroupId(String operateGroupId) {
		this.operateGroupId = operateGroupId;
	}

	@Column(name = "OPERATE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getOperateId() {
		return this.operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

}