package com.sinoframe.business;

import java.util.Map;

import com.sinoframe.bean.SysRelationRoleOperate;
import com.sinoframe.bean.SysRelationRoleOperateId;

public interface ISysRelationRoleOperateBS extends IService{
	

	/**
	 * getById
	 * 
	 * @param entity
	 */
	SysRelationRoleOperate getById(SysRelationRoleOperateId id);
	void deleteByLianHeIds(String ids);
}
