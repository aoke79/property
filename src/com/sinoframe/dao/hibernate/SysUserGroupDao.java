package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysUserGroupDao;

/**
 * SysUserGroup表操作实现类
 * @author kangjiwen
 *
 */
@Repository("sysUserGroupDao")
@Transactional
public class SysUserGroupDao extends PublicDao implements ISysUserGroupDao {

	public void clear(Class c){
		this.getHibernateTemplate().getSessionFactory().evict(c);
	}
}
