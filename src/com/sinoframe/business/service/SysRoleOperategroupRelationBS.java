package com.sinoframe.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysRoleOperategroupRelation;
import com.sinoframe.bean.SysRoleOperategroupRelationId;
import com.sinoframe.business.ISysRoleOperategroupRelationBS;
import com.sinoframe.dao.ISysRoleOperategroupRelationDao;

/**
 * SysRoleOperategroupRelationBS
 * @描述 角色操作组关系的业务处理类
 * @作者 胡星
 * @版本 1.0
 */
@Service("sysRoleOperategroupRelationBS")
public class SysRoleOperategroupRelationBS extends BaseBS implements
		ISysRoleOperategroupRelationBS {
	// 角色操作组关系的DAO
	ISysRoleOperategroupRelationDao sysRoleOperategroupRelationDao;

	public ISysRoleOperategroupRelationDao getSysRoleOperategroupRelationDao() {
		return sysRoleOperategroupRelationDao;
	}

	@Resource
	public void setSysRoleOperategroupRelationDao(
			ISysRoleOperategroupRelationDao sysRoleOperategroupRelationDao) {
		this.sysRoleOperategroupRelationDao = sysRoleOperategroupRelationDao;
	}

	/**
	 * delete SysRoleOperategroupRelation By LianHeIds
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public void deleteByLianHeIds(String ids) {
		// 联合 ID 主键
		SysRoleOperategroupRelationId sysRoleOperategroupRelationId;
		// 分离待删除的联合 ID 主键
		String[] tempStrs = ids.split(",");
		// 逐一取出联合 ID 主键
		for (String s : tempStrs) {
			// 创建联合 ID 主键，并赋值
			sysRoleOperategroupRelationId = new SysRoleOperategroupRelationId();
			String[] tempStrs1 = s.split("&sysRelationRoleOperateId.operateId=");
			System.err.println(tempStrs1[0]);
			System.err.println(tempStrs1[1]);
			sysRoleOperategroupRelationId.setRoleId(tempStrs1[1]);
			sysRoleOperategroupRelationId.setOperateGroupid(tempStrs1[0]);
			// 根据联合 ID 主键删除操作组关系表（联合 ID 实现了序列化）
			this.deleteById(SysRoleOperategroupRelation.class, sysRoleOperategroupRelationId);
		}
	}
}
