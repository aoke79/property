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
 * CmUserOperateRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CM_USER_OPERATE_RELATION")
public class CmUserOperateRelation implements java.io.Serializable {

	// Fields

	private CmUserOperateRelationId id;
	private CmUser cmUser;
	private SysOperate sysOperate;

	// Constructors

	/** default constructor */
	public CmUserOperateRelation() {
	}

	/** full constructor */
	public CmUserOperateRelation(CmUserOperateRelationId id, CmUser cmUser,
			SysOperate sysOperate) {
		this.id = id;
		this.cmUser = cmUser;
		this.sysOperate = sysOperate;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "id", column = @Column(name = "ID", unique = false, nullable = false, insertable = true, updatable = true, length = 3)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)) })
	public CmUserOperateRelationId getId() {
		return this.id;
	}

	public void setId(CmUserOperateRelationId id) {
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

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ID", unique = false, nullable = false, insertable = false, updatable = false)
	public SysOperate getSysOperate() {
		return this.sysOperate;
	}

	public void setSysOperate(SysOperate sysOperate) {
		this.sysOperate = sysOperate;
	}

}