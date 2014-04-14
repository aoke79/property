package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISecSerialnumberDao;
/**
 * SecSerialnumberDao
 * @描述 序列号的 DAO 处理类
 * @作者 胡星
 * @版本 1.0
 */
@Repository("secSerialnumberDao")
@Transactional
public class SecSerialnumberDao extends PublicDao implements
		ISecSerialnumberDao {

}
