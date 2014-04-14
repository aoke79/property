package com.sinoframe.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinoframe.business.ISysOperateGroupBS;
import com.sinoframe.dao.ISysOperateGroupDao;

/**
 * SysOperateGroupBS
 * @描述 操作组的业务处理类
 * @作者 胡星
 * @版本 1.0
 */
@Service("sysOperateGroupBS")
public class SysOperateGroupBS extends BaseBS implements ISysOperateGroupBS {
	// 操作组的DAO
	ISysOperateGroupDao sysOperateGroupDao;

	public ISysOperateGroupDao getSysOperateGroupDao() {
		return sysOperateGroupDao;
	}

	@Resource
	public void setSysOperateGroupDao(ISysOperateGroupDao sysOperateGroupDao) {
		this.sysOperateGroupDao = sysOperateGroupDao;
	}

}
