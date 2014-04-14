package com.sinoframe.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysRoleUserGroupRelation;
import com.sinoframe.business.ISysRoleUserGroupRelationBS;

@Service("roleUserGroupRelationBS")
@Transactional
public class SysRoleUserGroupRelationBS extends BaseBS implements ISysRoleUserGroupRelationBS {

	public void updateState(SysRoleUserGroupRelation sysRoleUserGroupRelation) {
		if("1".equals(sysRoleUserGroupRelation.getState())){
			sysRoleUserGroupRelation.setState("0");
		}else{
			sysRoleUserGroupRelation.setState("1");
		}
	}

}
