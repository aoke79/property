package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysUserGroupRelationDao;

@Repository("sysUserGroupRelationDao")
@Transactional
public class SysUserGroupRelationDao extends PublicDao implements ISysUserGroupRelationDao {

}
