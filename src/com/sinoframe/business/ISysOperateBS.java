package com.sinoframe.business;

import java.util.HashMap;
import java.util.List;

import com.sinoframe.bean.SysOperate;
import com.sinoframe.bean.SysOrderByInfo;
import com.sinoframe.bean.SysPageInfo;
import com.sinoframe.bean.SysRelationRoleOperate;
/**
 * ISysOperateBS
 * @描述 操作的业务处理接口
 * @作者 胡星
 * @版本 1.0
 */
public interface ISysOperateBS extends IService {
	/**
	 * find SysOperate List By Not In Ids
	 * 
	 * @param tempString
	 * @return
	 */
	public List<SysOperate> findByNotInIds(String tempString);

	/**
	 * get SysOperate List By UserId
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysOperate> getSysOperateByUser(String userId);
	/***
	 * 
	 * @param userId
	 * @param sid
	 * @return
	 */
	public List<SysOperate> getSysOperateByUser(String userId,String sid);
	
	/**
	 * 根据操作获取角色集合
	 * @param operateId
	 * @param sysPageInfo
	 * @param sysOrderByInfo
	 * @param query
	 * @return
	 */
	public List<SysRelationRoleOperate> rolesListByOperate(String operateId, SysPageInfo sysPageInfo,
			SysOrderByInfo sysOrderByInfo, HashMap<String, String> query);

	
	/**
	 * 操作获取角色
	 * @param operateId
	 * @param query
	 * @return
	 */
	public int rolesListCount(String operateId, HashMap<String, String> query);
}
