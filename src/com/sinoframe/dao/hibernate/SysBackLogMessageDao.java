/**
 * @Title BackLogMessage
 * @Description the implement class of the interface "IBackLogMessageDao"
 *              connect to the database and do the operations 
 */

package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysBackLogMessageDao;

@Repository("BackLogMessageDao")
@Transactional
public class SysBackLogMessageDao extends PublicDao implements ISysBackLogMessageDao {
	
}
