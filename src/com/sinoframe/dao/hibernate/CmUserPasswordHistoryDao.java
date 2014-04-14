package com.sinoframe.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sinoframe.dao.ICmUserPasswordHistoryDao;

@Repository("cmUserPasswordHistoryDao")
@Transactional
public class CmUserPasswordHistoryDao extends PublicDao implements
		ICmUserPasswordHistoryDao {

}
