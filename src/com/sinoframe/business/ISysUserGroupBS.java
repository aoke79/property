package com.sinoframe.business;

import com.sinoframe.bean.SysUserGroup;

public interface ISysUserGroupBS extends IService{
	
	/**
	 * 判断该用户组下是否有用户
	 */
	public boolean hasUser(String strId);
	/**
	 * 将用户添加到当前用户组
	 * @param ids 要添加的用户的id
	 * @param sysUserGroup 当前的用户组
	 */
	public void addUserToGroup(String ids, SysUserGroup sysUserGroup);
	
	/**
	 * 将角色分配给当前用户组
	 * @param ids 要添加的角色的id
	 * @param 当前用户组
	 */
	public void addRoleToGroup(String ids, SysUserGroup sysUserGroup);
	
}
