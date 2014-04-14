package com.sinoframe.business;

/**
 * ISysOperateGroupRelationBS
 * @描述 操作组关系的业务处理接口
 * @作者 胡星
 * @版本 1.0
 */
public interface ISysOperateGroupRelationBS extends IService {
	/**
	 * delete SysOperateGroupRelation By LianHeIds
	 * 
	 * @param ids
	 * @return
	 */
	void deleteByLianHeIds(String ids);

}
