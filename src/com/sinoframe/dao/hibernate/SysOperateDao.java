package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysOperateDao;

/**
 * SysOperateDao
 * @描述 操作的 DAO 处理类
 * @作者 胡星
 * @版本 1.0
 */
@Repository("sysOperateDao")
@Transactional
public class SysOperateDao extends PublicDao implements ISysOperateDao {

}
