package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysOperateGroupRelationDao;

/**
 * SysOperateGroupRelationDao
 * @描述 操作组关系的 DAO 处理类
 * @作者 胡星
 * @版本 1.0
 */
@Repository("sysOperateGroupRelationDao")
@Transactional
public class SysOperateGroupRelationDao extends PublicDao implements
		ISysOperateGroupRelationDao {

}
