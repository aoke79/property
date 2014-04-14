package com.sinoframe.business;

/**
 * ISysRoleOperategroupRelationBS
 * @描述 角色操作组关系的业务处理接口
 * @作者 胡星
 * @版本 1.0
 */
public interface ISysRoleOperategroupRelationBS extends IService {

	/**
	 * delete SysRoleOperategorupRelation By LianHeIds
	 * 
	 * @param ids
	 * @return
	 */
	void deleteByLianHeIds(String ids);
}
