/**
 * Title: SysSystemDao
 * Description: the implement of the interface "ISysSystemDao"
 */

package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysSystemDao;

@Repository("SysSystemDao")
@Transactional
public class SysSystemDao extends PublicDao implements ISysSystemDao {
	
}
