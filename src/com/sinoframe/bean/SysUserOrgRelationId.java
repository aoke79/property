package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysUserOrgRelationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysUserOrgRelationId implements java.io.Serializable {

	// Fields

	private String userId;
	private String orgId;

	// Constructors

	/** default constructor */
	public SysUserOrgRelationId() {
	}

	/** use constructor */
	public SysUserOrgRelationId(String orgId) {
		this.userId = userId;
		this.orgId = orgId;
	}
	/** full constructor */
	public SysUserOrgRelationId(String userId, String orgId) {
		this.userId = userId;
		this.orgId = orgId;
	}

	// Property accessors

	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysUserOrgRelationId))
			return false;
		SysUserOrgRelationId castOther = (SysUserOrgRelationId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(
				castOther.getUserId())))
				&& ((this.getOrgId() == castOther.getOrgId()) || (this
						.getOrgId() != null
						&& castOther.getOrgId() != null && this.getOrgId()
						.equals(castOther.getOrgId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getOrgId() == null ? 0 : this.getOrgId().hashCode());
		return result;
	}

}