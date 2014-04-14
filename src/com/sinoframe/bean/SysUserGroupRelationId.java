package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysUserGroupRealtionId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysUserGroupRelationId implements java.io.Serializable {

	// Fields

	private String groupId;
	private String userId;

	// Constructors

	/** default constructor */
	public SysUserGroupRelationId() {
	}

	/** full constructor */
	public SysUserGroupRelationId(String groupId, String userId) {
		this.groupId = groupId;
		this.userId = userId;
	}

	// Property accessors

	@Column(name = "GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysUserGroupRelationId))
			return false;
		SysUserGroupRelationId castOther = (SysUserGroupRelationId) other;

		return ((this.getGroupId() == castOther.getGroupId()) || (this
				.getGroupId() != null
				&& castOther.getGroupId() != null && this.getGroupId().equals(
				castOther.getGroupId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

	public String toString(){
		return groupId + ";" + userId;
	}
}