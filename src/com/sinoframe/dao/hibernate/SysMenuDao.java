/**
 * Title: SysMenuDao
 * Description: the implement of the interface "ISysMenuDao"
 */

package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysMenuDao;

@Repository("SysMenuDao")
@Transactional
public class SysMenuDao extends PublicDao implements ISysMenuDao {
	
}
