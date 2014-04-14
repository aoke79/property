package com.sinoframe.business;

import java.util.Map;

import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.CmUserOperateRelation;
import com.sinoframe.bean.CmUserOperateRelationId;

public interface ICmUserOperateRelationBS extends IService{
	
	/**
	 * save/insert into DB
	 * 
	 * @param entity
	 * @return
	 */
	void doAdd(CmUserOperateRelation cmUserOperateRelation);

	/**
	 * getById
	 * 
	 * @param entity
	 */
	CmUserOperateRelation getById(CmUserOperateRelationId id);

	/**
	 * update
	 * 
	 * @param entity
	 */
	void doUpdate(CmUserOperateRelation cmUserOperateRelation);

	/**
	 * delete by Entity
	 * 
	 * @param entity
	 */
	void doDel(CmUserOperateRelation cmUserOperateRelation);

	
	void deleteByLianHeIds(String ids);

}
