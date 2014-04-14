package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysRoleUserGroupRelationDao;

@Repository("sysRoleUserGroupRelationDao")
@Transactional
public class SysRoleUserGroupRelationDao extends PublicDao implements ISysRoleUserGroupRelationDao {
   
}
