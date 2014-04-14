package com.sinoframe.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * 联合主键类
 * @author niujingwei
 *
 */
@Embeddable
public class SysRAccountRoleId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778945165952082375L;

	//Fileds

	//用户ID
	private String userId;

	//角色ID
	private String roleId;

	public SysRAccountRoleId() {

	}
	
	

	public SysRAccountRoleId(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}
	
	@Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Column(name = "ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 36)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SysRAccountRoleId))
			return false;
		SysRAccountRoleId castOther = (SysRAccountRoleId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId()
				.equals(castOther.getUserId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null
						&& castOther.getRoleId() != null && this.getRoleId()
						.equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}
	

}
