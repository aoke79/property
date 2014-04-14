package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SysRoleUsergroupRelationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class SysRoleUserGroupRelationId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String groupId;

	// Constructors

	/** default constructor */
	public SysRoleUserGroupRelationId() {
	}

	/** full constructor */
	public SysRoleUserGroupRelationId(String roleId, String groupId) {
		this.roleId = roleId;
		this.groupId = groupId;
	}

	// Property accessors

	@Column(name = "ROLE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "GROUP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRoleUserGroupRelationId))
			return false;
		SysRoleUserGroupRelationId castOther = (SysRoleUserGroupRelationId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(
				castOther.getRoleId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null
						&& castOther.getGroupId() != null && this.getGroupId()
						.equals(castOther.getGroupId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		return result;
	}
	
	public String toString(){
		return roleId + ";" + groupId;
	}

}