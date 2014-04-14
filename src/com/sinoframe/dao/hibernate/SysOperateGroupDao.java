package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysOperateGroupDao;

/**
 * SysOperateGroupDao
 * @描述 操作组的 DAO 处理类
 * @作者 胡星
 * @版本 1.0
 */
@Repository("sysOperateGroupDao")
@Transactional
public class SysOperateGroupDao extends PublicDao implements
		ISysOperateGroupDao {

}
