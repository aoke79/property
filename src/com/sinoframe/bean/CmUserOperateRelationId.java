package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CmUserOperateRelationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class CmUserOperateRelationId implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;

	// Constructors

	/** default constructor */
	public CmUserOperateRelationId() {
	}

	/** full constructor */
	public CmUserOperateRelationId(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}

	// Property accessors

	@Column(name = "ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}