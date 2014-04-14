package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysUserOrgRelationDao;

@Transactional
@Repository("sysUserOrgRelationDao")
public class SysUserOrgRelationDao extends PublicDao implements
		ISysUserOrgRelationDao {

}
