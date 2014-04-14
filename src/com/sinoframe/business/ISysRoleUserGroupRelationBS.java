package com.sinoframe.business;

import com.sinoframe.bean.SysRoleUserGroupRelation;


/**
 * 
 * @author kangjiwen
 *
 */
public interface ISysRoleUserGroupRelationBS extends IService {

	// 修改用户组角色关系的状态
	public void updateState(SysRoleUserGroupRelation sysRoleUserGroupRelation);

}
