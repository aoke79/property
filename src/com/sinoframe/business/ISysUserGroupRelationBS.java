package com.sinoframe.business;

import com.sinoframe.bean.SysUserGroupRelation;

public interface ISysUserGroupRelationBS extends IService {

	/**
	 * 更改当前用户用户组关系的状态
	 */
	
	public void updateState(SysUserGroupRelation SysUserGroupRelation);
}
