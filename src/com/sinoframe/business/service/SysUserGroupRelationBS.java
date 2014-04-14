package com.sinoframe.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.bean.SysUserGroupRelation;
import com.sinoframe.business.ISysUserGroupRelationBS;
import com.sinoframe.dao.ISysUserGroupRelationDao;

@Service("sysUserGroupRelationBS")
@Transactional
public class SysUserGroupRelationBS extends BaseBS implements ISysUserGroupRelationBS {
	
	ISysUserGroupRelationDao sysUserGroupRelationDao ;

	@Override
	public void updateState(SysUserGroupRelation sysUserGroupRelation) {
		if("1".equals(sysUserGroupRelation.getState())){
			sysUserGroupRelation.setState("0");
		}else if("0".equals(sysUserGroupRelation.getState())){
			sysUserGroupRelation.setState("1");
		}
	}
	
}
