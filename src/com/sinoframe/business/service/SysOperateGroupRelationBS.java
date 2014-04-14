package com.sinoframe.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.bean.SysOperateGroupRelation;
import com.sinoframe.bean.SysOperateGroupRelationId;
import com.sinoframe.business.ISysOperateGroupRelationBS;
import com.sinoframe.dao.ISysOperateGroupRelationDao;

/**
 * SysOperateGroupRelationBS
 * @描述 操作组关系的业务处理类
 * @作者 胡星
 * @版本 1.0
 */
@Service("sysOperateGroupRelationBS")
public class SysOperateGroupRelationBS extends BaseBS implements
		ISysOperateGroupRelationBS {
	// 操作组关系的DAO
	ISysOperateGroupRelationDao sysOperateGroupRelationDao;

	public ISysOperateGroupRelationDao getSysOperateGroupRelationDao() {
		return sysOperateGroupRelationDao;
	}

	@Resource
	public void setSysOperateGroupRelationDao(
			ISysOperateGroupRelationDao sysOperateGroupRelationDao) {
		this.sysOperateGroupRelationDao = sysOperateGroupRelationDao;
	}

	/**
	 * delete SysOperateGroupRelation By LianHeIds
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public void deleteByLianHeIds(String ids) {
		// 操作组关系表的 ID 联合主键
		SysOperateGroupRelationId sysOperateGroupRelationId;
		// 分离待删除的联合 ID 主键
		String[] tempStrs = ids.split(",");
		// 逐一取出联合 ID 主键
		for (String s : tempStrs) {
			// 创建联合 ID 主键，并赋值
			sysOperateGroupRelationId = new SysOperateGroupRelationId();
			String[] tempStrs1 = s.split("&sysRelationRoleOperateId.operateId=");
			System.err.println(tempStrs1[0]);
			System.err.println(tempStrs1[1]);
			sysOperateGroupRelationId.setOperateGroupId(tempStrs1[0]);
			sysOperateGroupRelationId.setOperateId(tempStrs1[1]);
			// 根据联合 ID 主键删除操作组关系表（联合 ID 实现了序列化）
			this.deleteById(SysOperateGroupRelation.class, sysOperateGroupRelationId);
		}
	}

}
