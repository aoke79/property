package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ISysHistoryMessageDao;

@Repository("historyMessageDao")
@Transactional
public class SysHistoryMessageDao extends PublicDao implements ISysHistoryMessageDao {
	
}
