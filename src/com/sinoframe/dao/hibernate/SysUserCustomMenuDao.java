/**
 * Title: SysUserCustomMenuDao
 * Description: the implement of the interface "ISysUserCustomMenuDao"
 */

package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysUserCustomMenuDao;

@Repository("sysUserCustomMenuDao")
@Transactional
public class SysUserCustomMenuDao extends PublicDao implements ISysUserCustomMenuDao {
	
}
